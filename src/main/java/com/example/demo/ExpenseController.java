package com.example.demo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	// EndPoint to get all expenses
	@GetMapping("/getExpenses")
	public List<Expense> getExpenses(HttpServletRequest request) {
		return expenseService.getExpenses(request);
	}

	// EndPoint to get expenses by category
	@GetMapping("/getExpensesbyCategory/{category}")
	public List<Expense> getExpensesbyCategory(@PathVariable String category, HttpServletRequest request) {
		return expenseService.getExpensebyCategory(category, request);
	}

	// EndPoint to add a new expense
	@PostMapping("/addExpenses")
	public String addExpenses(@RequestBody Expense expense, HttpServletRequest request) {
		expense.setCreatedAt(LocalDateTime.now());
		expenseService.addExpense(expense, request);
		return "Expense added Successfully";
	}

	// EndPoint to update an existing expense
	@PutMapping("/updateExpenses/{id}")
	public ResponseEntity<String> updateExpense(@PathVariable int id, @RequestBody Expense expense,
			HttpServletRequest request) {
		// expense.setModifyAt(LocalDateTime.now());
		String result = expenseService.updateExpense(id, expense, request);
		if ("Updated Successfully".equals(result)) {
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else if ("ID does not exist".equals(result)) {
			return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// EndPoint to delete an expense by id
	@DeleteMapping("/deleteExpenses/{id}")
	public String deleteExpense(@PathVariable int id, HttpServletRequest request) {
		expenseService.deleteExpense(id, request);
		return "Expense Deleted Successfully";
	}

	@GetMapping("/getWeeklyReport")
	public List<Expense> getWeeklyReport(HttpServletRequest request) {
		return expenseService.getWeeklyReport(request);
	}

	@GetMapping("/getMonthlyReport")
	public List<Expense> getMonthlyReport(HttpServletRequest request) {
		return expenseService.getMonthlyReport(request);
	}

//	@GetMapping("/timePeriod/{start}/{end}")
//	public List<Expense> getReport(@PathVariable LocalDateTime start, @PathVariable LocalDateTime end,
//			HttpServletRequest request) {
//		return expenseService.getExpensesWithinPeriod(start, end, request);
//	}

	@GetMapping("/totalExpensePrice")
	public int getTotalExpensePrice(HttpServletRequest request) {
		return expenseService.getTotalExpensePrice(request);
	}

	@GetMapping("/weeklyExpensePrice")
	public int getWeeklyExpense(HttpServletRequest request) {
		return expenseService.getTotalWeeklyExpensePrice(request);
	}

	@GetMapping("/monthlyExpensePrice")
	public int getMonthlyExpense(HttpServletRequest request) {
		return expenseService.getTotalMonthlyExpensePrice(request);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
