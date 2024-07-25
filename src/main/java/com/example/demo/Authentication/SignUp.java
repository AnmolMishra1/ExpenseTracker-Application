package com.example.demo.Authentication;

import java.util.List;

import com.example.demo.Expense;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SignUp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String name;
	private String password;
	private String email;
	@OneToMany(mappedBy = "user")
	private List<Expense> expenses;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SignUp(Long id, String username, String name, String email, String password) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "SignUp [id=" + id + "username=" + username + "email=" + email + ", name=" + name + ", password="
				+ password + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
