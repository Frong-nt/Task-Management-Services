package com.example.MemberService;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Member {
	@Id
	private int id;
	

	private String firstname;


	private String lastname;
	

	private String role;
	

	private String hours;
	

	private String email;
	
	public Member() {
	}
	public Member(int id,String firstname,String lastname, String role,String hours, String email){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
		this.hours = hours;
		this.email = email;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
}

