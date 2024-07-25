package com.example.demo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Authentication.SignUp;
import com.example.demo.Authentication.SignUpRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository; // Dependency injection for ExpenseRepository

	@Autowired
	private SignUpRepository signUpRepository; // Dependency injection for SignUpRepository

	private Long getCurrentUserId(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("userId") != null) {
			return (Long) session.getAttribute("userId");
		}
		return null;
	}

	// Method to get all expenses for the current user
	public List<Expense> getExpenses(HttpServletRequest request) {
		List<Expense> expenses = new ArrayList<>();
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			expenseRepository.findByUserId(userId).forEach(expenses::add);
		}
		// expenseRepository.findAll().forEach(expenses::add);
		return expenses;

	}

	// Method to get expenses by category for the current user
	public List<Expense> getExpensebyCategory(String category, HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			return expenseRepository.findByUserId(userId).stream()
					.filter(expense -> expense.getCategory().equals(category)).collect(Collectors.toList());
		}
		return new ArrayList<>();
	}

	// Method to add a new expense for the current user
	public String addExpense(Expense expense, HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			SignUp user = signUpRepository.findById(userId).orElse(null);
			if (user != null) {
				expense.setUser(user);
				expenseRepository.save(expense);
				return "Expense added successfully";
			}
			return "User not found";
		}
		return "User not logged in";
	}

	// Method to update an existing expense for the current user
	public String updateExpense(int id, Expense expense, HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			Optional<Expense> optionalExpense = expenseRepository.findById(id);
			if (optionalExpense.isPresent()) {
				Expense existingExpense = optionalExpense.get();
				if (existingExpense.getUser().getId().equals(userId)) {
					existingExpense.setCategory(expense.getCategory());
					existingExpense.setPrice(expense.getPrice());
					existingExpense.setName(expense.getName());
					existingExpense.setModifyAt(LocalDateTime.now());
					expenseRepository.save(existingExpense);
					return "Updated Successfully";
				}
				return "Unauthorized access";
			}
			return "Expense does not exist";
		}
		return "User not logged in";
	}

	// Method to delete an expense by id for the current user
	public String deleteExpense(int id, HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			Optional<Expense> optionalExpense = expenseRepository.findById(id);
			if (optionalExpense.isPresent()) {
				Expense existingExpense = optionalExpense.get();
				if (existingExpense.getUser().getId().equals(userId)) {
					expenseRepository.deleteById(id);
					return "Deleted Successfully";
				}
				return "Unauthorized access";
			}
			return "Expense does not exist";
		}
		return "User not logged in";
	}

	// Method to get weekly report for the current user
	public List<Expense> getWeeklyReport(HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startOfWeek = now.with(java.time.DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
			return getExpensesWithinPeriod(startOfWeek, now, userId);
		}
		return new ArrayList<>();
	}

	// Method to get monthly report for the current user
	public List<Expense> getMonthlyReport(HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startOfMonth = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
			return getExpensesWithinPeriod(startOfMonth, now, userId);
		}
		return new ArrayList<>();
	}

	// Helper method to get expenses within a specified period for the current user
	public List<Expense> getExpensesWithinPeriod(LocalDateTime start, LocalDateTime end, Long userId) {
		return expenseRepository.findByUserId(userId).stream()
				.filter(expense -> expense.getCreatedAt().isAfter(start) && expense.getCreatedAt().isBefore(end))
				.collect(Collectors.toList());
	}

	// Method to get Total Expense Price for the current user
	public int getTotalExpensePrice(HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			return expenseRepository.findByUserId(userId).stream().mapToInt(Expense::getPrice).sum();
		}
		return 0;
	}

	// Method to calculate total weekly expense price for the current user
	public int getTotalWeeklyExpensePrice(HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startOfWeek = now.with(java.time.DayOfWeek.MONDAY).toLocalDate().atStartOfDay();
			return getExpensesWithinPeriod(startOfWeek, now, userId).stream().mapToInt(Expense::getPrice).sum();
		}
		return 0;
	}

	// Method to calculate total monthly expense price for the current user
	public int getTotalMonthlyExpensePrice(HttpServletRequest request) {
		Long userId = getCurrentUserId(request);
		if (userId != null) {
			LocalDateTime now = LocalDateTime.now();
			LocalDateTime startOfMonth = now.withDayOfMonth(1).toLocalDate().atStartOfDay();
			return getExpensesWithinPeriod(startOfMonth, now, userId).stream().mapToInt(Expense::getPrice).sum();
		}
		return 0;
	}
}
