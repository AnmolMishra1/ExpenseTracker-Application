package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Integer> { // inheriting CrudRepository interface to
																				// create Expense Database;

	List<Expense> findByUserId(Long userId);

	public static void main(String[] args) {

	}

}
