package com.example.demo.model;
import javax.persistence.*;

@Entity
@Table
public class Role {

	@Id
	@GeneratedValue
	private Long roleID;
	private String roleName;
	
	public Role() {}

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}
	
	
	public Long getRoleID() {
		return roleID;
	}
	public void setRoleID(Long roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
