package com.example.demo.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
	@Autowired
	SignUpService signUpService;

	@PostMapping("/signUp")
	public String signUp(@RequestBody SignUp signUp) {
		return signUpService.addSignUp(signUp);
	}

	@DeleteMapping("/deleteAccount")
	public String deleteAccount(@RequestBody SignUp signUp) {
		return signUpService.deleteSignUp(signUp);
	}

//
//	@PostMapping("/login")
//	public String login(@RequestBody Login login) {
//		return signUpService.login(login);
//	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login) {
		try {
			String response = signUpService.login(login);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
		}
	}

	public static void main(String[] args) {

	}

}
