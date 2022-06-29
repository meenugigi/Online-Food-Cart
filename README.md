# Online-Food-Cart

This is a Java spring boot application. 
The application has been implemented using Spring Web MVC, Spring Security, Spring JDBC, Thymeleaf template, REST services, Hibernate and Tomcat.

This application supports User and ADMIN roles. Users can view restaurants, restaurant menus, add items to the cart and place orders.
ADMIN can add and delete restaurants, add, update or delete a restaurant menu, add items to cart and place orders.

It follows the Spring MVC framework.
The Model classes create the database tables and the service layer acts as the middleware between the controller and the repository.
The Thymeleaf template handles the view part of the application.
The database connection is implemented in the application.properties file.
The repository class makes calls to the database and the results are returned back to the service layer.


