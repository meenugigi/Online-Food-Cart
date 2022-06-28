package com.example.demo.model;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "User", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue
	private Long userID;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String contact;
	
//	to create manytomany relation between the user and role tables.
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="users_role",
			joinColumns = @JoinColumn(
					name = "user_id", referencedColumnName = "userID"),
			inverseJoinColumns = @JoinColumn(
					name = "role_id", referencedColumnName = "roleID"))
	private Collection<Role> roles;
	
	public User() {}
	
	


	public User(String firstName, String lastName, String email, String password, String contact,
			Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.roles = roles;
	}










	public Long getUserID() {
		return userID;
	}



	public void setUserID(Long userID) {
		this.userID = userID;
	}



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}



	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}


	
	
	
	
}
