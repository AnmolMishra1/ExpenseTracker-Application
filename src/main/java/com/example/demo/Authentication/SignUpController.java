package com.example.demo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
public class SignUpController {

	@Autowired
	SignUpService signUpService; // Injecting Dependency for SignUpService

	// EndPoint to handle user sign-up
	@PostMapping("/signUp")
	public String signUp(@RequestBody SignUp signUp, HttpServletRequest request) {
		return signUpService.addSignUp(signUp, request);
	}

	// EndPoint to delete a user account
	@DeleteMapping("/deleteAccount")
	public String deleteAccount(@RequestBody SignUp signUp) {
		return signUpService.deleteSignUp(signUp);
	}

	// EndPoint to change user password
	@PutMapping("/changePassword")
	public String changePassword(@RequestBody ChangePassword changePassword) {
		return signUpService.changePassword(changePassword);
	}

	// EndPoint to handle user login
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login, HttpServletRequest request) {
		try {
			String response = signUpService.login(login, request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
		}
	}

	// EndPoint to handle user logout
	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		return signUpService.logout(request);
	}

	public static void main(String[] args) {

	}
}
