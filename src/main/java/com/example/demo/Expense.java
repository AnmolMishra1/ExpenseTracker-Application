package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Expense {
	@Id
	private int id;
	private String name;
	private String category;
	private String date;
	private int price;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense(int id, String name, String category, String date, int price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.date = date;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", name=" + name + ", category=" + category + ", date=" + date + ", price=" + price
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
