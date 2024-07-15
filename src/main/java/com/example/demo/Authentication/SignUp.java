package com.example.demo.Authentication;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SignUp {
	private String name;
	private String password;
	@Id
	private String email;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUp(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignUp [email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
