package com.example.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.catalina.startup.HomesUserDatabase;
import org.hibernate.graph.CannotContainSubGraphException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.remoting.soap.SoapFaultException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.model.ItemInfo;
import com.example.demo.model.Orders;
import com.example.demo.model.Restaurants;
import com.example.demo.model.UserCart;
import com.example.demo.repository.ItemRepository;
import com.example.demo.service.FoodCartService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.util.Converter;

@Controller
public class CartController {

	public static String uploadDirectory=System.getProperty("user.dir")+"/src/main/resources/static";
	
	@Autowired
	private UserService uService;
	
	@Autowired
	private FoodCartService fService;
	

	
	   /**
     * Constructor
     */
	public CartController(UserService uService, FoodCartService fService) {
		super();
		this.uService = uService;
		this.fService = fService;
	}
	
//-----------------------------------------------------------------------------------------------------
	   /**
     * displays the dashboard with names of all restaurants.
     */
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mView = new ModelAndView("home");
		mView.addObject("restaurants", fService.findAllRestaurants());
		return mView;
	}
	
//-------------------------------------------------------------------------------------------------------
	
	   /**
     * Displays the menu of each restaurant.
     * If a restaurant has no menu, it will display a message 'nothing to display'.
     * Else, it will display a list of all items provided by that particular restaurant.
     */
	@GetMapping("/view-menu/{restaurantID}")
	public ModelAndView viewRestaurantMenu(@PathVariable ( value = "restaurantID") Long restaurantID) {
		ModelAndView mView = new ModelAndView("restaurant_menu");
		List<ItemInfo> items = fService.findRestaurantMenu(restaurantID);
		if(items.isEmpty()) {
			String show = "Nothing to display";
			mView.addObject("show", show);
		}
		mView.addObject("items", fService.findRestaurantMenu(restaurantID));
		return mView;
	}
	
	
	
	   /**
     * Saves a new restaurant to the database.
     */
	@PostMapping("/admin/save-restaurant")
	public String saveRestaurant(Model model, Restaurants restaurant, ItemInfo items, @RequestParam("restCoverImage") MultipartFile restImagefile, 
			@RequestParam("itemImage") MultipartFile itemImageFile) {		
		restaurant.setRestCoverImage(uploadImage(restImagefile));	
		fService.saveRestaurantToDB(restaurant);
		items.setItemImage(uploadImage(itemImageFile));
		items.setRestaurants(restaurant);
		System.out.print("---------------"+items);
		System.out.print(items.getRestaurants());
		fService.saveItem(items);
		model.addAttribute("items");
		return "redirect:/";
	}
	
	
	   /**
     * Uploads the image file to the database.
     */
	private String uploadImage(MultipartFile restImagefile) {
		// TODO Auto-generated method stub
		StringBuilder fileNames = new StringBuilder();
		String filename = restImagefile.getOriginalFilename();
		Path fileNameAndPath = Paths.get(uploadDirectory, filename);
		try {
			Files.write(fileNameAndPath, restImagefile.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filename;
	}

	
	
	   /**
     * Displays the template required to allow admin to create a new restaurant.
     */
	@GetMapping("/admin/add-restaurant")
	public ModelAndView adminAddRestaurant() {
		ModelAndView mView = new ModelAndView("admin_addRestaurant");
		return mView;
	}
	
	
	   /**
     * Deletes a restaurant from the list of restaurants.
     */
	@GetMapping("/admin/delete-restaurant/{restaurantID}")
	public String deleteRestaurant(@PathVariable ( value = "restaurantID") long restaurantID, RedirectAttributes attributes) {
		List<ItemInfo> menu = fService.findRestaurantMenu(restaurantID);
		System.out.println("-------------"+ menu.size());
			fService.deleteRestaurant(restaurantID);
		return "redirect:/";
	}
	
	


//------------------------------------------------------------------------------------------------------
	
	   /**
     * Displays template required to allow admin add new items to restaurant menu.
     */
	@GetMapping("/admin/add-items")
	public ModelAndView addItems() {
		ModelAndView mView = new ModelAndView("admin_addItems");
		List<String> restaurantName = fService.getRestaurantNames();
		mView.addObject("names", restaurantName);
		return mView;
	}
	
	
	   /**
     * Saves the newly added item to the corresponding restaurant's menu.
     */
	@PostMapping("/admin/add-items")
	@ResponseBody
	public ModelAndView addItems(@ModelAttribute("Items") ItemInfo item,Restaurants restaurants, 
			@RequestParam("itemImage") MultipartFile file, RedirectAttributes attributes) {
		item.setItemImage(uploadImage(file));	
		ModelAndView mView = new ModelAndView("redirect:/admin/add-items?success");
		restaurants = fService.getRestaurantByName(item.getRestaurants().getRestaurantName());
		item.setRestaurants(restaurants);
		fService.saveItem(item);
		mView.addObject("Items");
		return mView;	
	}
	
	
	   /**
     * Allow admin to delete a menu from the list of the restaurant's menu.
     */
	@GetMapping("/admin/delete/{itemID}")
	public String deleteItems(@PathVariable long itemID) {	
		fService.deleteItem(itemID);
		return "redirect:/";
	}
	
	
	
	   /**
  * Allow admin to update a menu from the list of the restaurant's menu.
  */
	@GetMapping("/admin/update-item/{itemID}")
	public ModelAndView updateItems(Restaurants restaurants, @PathVariable ( value = "itemID") long itemID) {
		ModelAndView mView = new ModelAndView("admin_updateItem");
		ItemInfo product = fService.getItemById(itemID);
		mView.addObject("product", product);
		return mView;
	}

	
	
	   /**
  * Save the updates made to the restaurant's menu by the admin.
  */
	@PostMapping("/save-updates")
	public String saveItem(@ModelAttribute("product") ItemInfo product) {
		fService.saveItem(product);
		return "redirect:/";
	}

//------------------------------------------------------------------------------------------------------	
	
	
	   /**
  * Add item to the shopping cart.
  */
	@GetMapping("/add-to-cart/{itemID}/{userName}")
	public String AddToCart(Model model, @PathVariable ( value = "itemID") long itemID, @PathVariable ( value = "userName") String userName, UserCart cart) {
		ItemInfo product = fService.getItemById(itemID);
		int count = fService.getItemCountInCart_add(userName, itemID);
		if(count == 0) {
			cart.setItemQuantity(1);
			cart.setItems(product);
			fService.saveItemToCart(cart);
		}
		else if(count > 0){
			fService.AddItemQuantityInCart(userName, itemID, count+1);
		}
		float cartAmount = fService.getCartTotal(userName);
		cart.setTotalAmount(cartAmount);
		model.addAttribute("cartAmount", cart);
		model.addAttribute("mycart", fService.showMyCart(userName));
		return "redirect:/";	
	}
	
	
	
	   /**
  * Displays the user cart.
  */
	@GetMapping("/view-cart/{userName}")
	public ModelAndView viewMyCart(@PathVariable ( value = "userName") String userName, UserCart userCart) {
		ModelAndView mView = new ModelAndView("myCart");
		List<UserCart> cart = fService.showMyCart(userName);
		mView.addObject("mycart", cart);
		float cartAmount = fService.getCartTotal(userName);
		userCart.setTotalAmount(cartAmount);
		mView.addObject("cartAmount", userCart);
		return mView;
	}
	
	
	
	   /**
  * Remove an item from the shopping cart.
  */
	@GetMapping("/view-cart/delete/{userName}/{cartID}")
	public ModelAndView removeFromCart(@PathVariable ( value = "userName") String userName, @PathVariable long cartID, UserCart userCart) {
		ModelAndView mView = new ModelAndView("myCart");
		int count = fService.getItemCountInCart_remove(userName, cartID);
		if(count==1) {
			fService.removeFromCart(cartID);
		}
		else if(count > 0) {
			fService.ReduceItemQuantityInCart(userName, cartID, count-1);
		}	
		List<UserCart> cart = fService.showMyCart(userName);
		mView.addObject("mycart", cart);
		
		float cartAmount = fService.getCartTotal(userName);
		userCart.setTotalAmount(cartAmount);
		mView.addObject("cartAmount", userCart);
		return mView;
	} 
	
//-----------------------------------------------------------------------------------------------------
	
	
	   /**
  * Place an order with the items present in the user cart.
  */
	@GetMapping("/place-order/{userName}")
	public ModelAndView placeOrder(@PathVariable ( value = "userName") String userName, Orders order) {
		ModelAndView mView = new ModelAndView("orderPlaced");
		List<UserCart> cartItems = fService.getAllCartItems(userName);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		   LocalDateTime now = LocalDateTime.now();
		   String userContactString = uService.getUserContact(userName);
		for(UserCart items: cartItems) {
			String uniqueID = UUID.randomUUID().toString();
			order.setOrderID(uniqueID);
			
			ItemInfo itemsOrdered = items.getItems();
			
			order.setItemID(itemsOrdered.getItemID());
			
			order.setUserEmail(items.getUserName());
			order.setQuantity(items.getItemQuantity());
			order.setContact(userContactString);
			order.setOrderDate(dtf.format(now));
			System.out.println(order);
			fService.saveOrder(order);
			fService.deleteOrderedItems(items.getCartID());
		}
		System.out.println(userContactString);
		System.out.println(cartItems.size());
		return mView;			
	}
}
