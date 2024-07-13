package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/getExpenses")
	public List<Expense> getExpenses() {
		return expenseService.getExpenses();
	}

	@GetMapping("/getExpensesbyCategory/{category}")
	public Expense getExpensesbyCategory(@PathVariable String category) {
		return expenseService.getExpensebyCategory(category);
	}

	@PostMapping("/addExpenses")
	public String addExpenses(@RequestBody Expense expense) {
		expenseService.addExpense(expense);
		return "Expense added Successfully";
	}

	@PutMapping("/updateExpenses/{id}")
	public String updateExpense(@PathVariable int id, @RequestBody Expense expense) {
		expenseService.updateExpense(id, expense);
		return "Expense Updated Successfully";
	}

	@DeleteMapping("/deleteExpenses/{id}")
	public String deleteExpense(@PathVariable int id) {
		expenseService.deleteExpense(id);
		return "Expense Deleted Successfully";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
