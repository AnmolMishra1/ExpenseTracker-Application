package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {
	@Autowired
	private ExpenseRepository expenseRepository;

	public List<Expense> getExpenses() {
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findAll().forEach(expenses::add);
		return expenses;
	}

	public Expense getExpensebyCategory(String category) {
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findAll().forEach(expenses::add);
		Expense expense = expenses.stream().filter(n -> n.getCategory().equals(category)).findFirst().get();
		return expense;
	}

	public void addExpense(Expense expense) {
		expenseRepository.save(expense);
	}

	public void updateExpense(int id, Expense expense) {
		expenseRepository.save(expense);
	}

	public void deleteExpense(int id) {
		expenseRepository.deleteById(id);
	}

	public static void main(String[] args) {

	}

}
