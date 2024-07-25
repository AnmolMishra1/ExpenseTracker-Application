package com.example.demo;

import java.time.LocalDateTime;

import com.example.demo.Authentication.SignUp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String category;
	private int price;
	private LocalDateTime CreatedAt;
	private LocalDateTime ModifyAt;

	@ManyToOne
	private SignUp user;

	public SignUp getUser() {
		return user;
	}

	public void setUser(SignUp user) {
		this.user = user;
	}

	// Default constructor
	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized constructor
	public Expense(Long id, String name, String category, int price, LocalDateTime createdAt, LocalDateTime modifyAt) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		CreatedAt = createdAt;
		ModifyAt = modifyAt;
	}

	// toString method for displaying Expense details
	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", category=" + category + ", price=" + price + ", CreatedAt="
				+ CreatedAt + ", ModifyAt=" + ModifyAt + "]";
	}

	// Getter for id
	public Long getId() {
		return id;
	}

	// Setter for id
	public void setId(Long id) {
		this.id = id;
	}

	// Getter for name
	public String getName() {
		return name;
	}

	// Setter for name
	public void setName(String name) {
		this.name = name;
	}

	// Getter for category
	public String getCategory() {
		return category;
	}

	// Setter for category
	public void setCategory(String category) {
		this.category = category;
	}

	// Getter for price
	public int getPrice() {
		return price;
	}

	// Setter for price
	public void setPrice(int price) {
		this.price = price;
	}

	// Getter for CreatedAt
	public LocalDateTime getCreatedAt() {
		return CreatedAt;
	}

	// Setter for CreatedAt
	public void setCreatedAt(LocalDateTime createdAt) {
		CreatedAt = createdAt;
	}

	// Getter for ModifyAt
	public LocalDateTime getModifyAt() {
		return ModifyAt;
	}

	// Setter for ModifyAt
	public void setModifyAt(LocalDateTime modifyAt) {
		ModifyAt = modifyAt;
	}
}
