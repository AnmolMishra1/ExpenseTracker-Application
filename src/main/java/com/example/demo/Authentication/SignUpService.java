package com.example.demo.Authentication;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
	@Autowired
	SignUpRepository signUpRepository; // creating object of signUp Service using SpringBoot Autowired;

	public String addSignUp(SignUp signUp) {
		try {
			// fetch existing data
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp1 -> signUp1.getEmail().equals(signUp.getEmail())).findFirst();

			if (optionalSignUp.isPresent()) {// checking email already used or not
				return "Email already Used";
			} else {
				signUpRepository.save(signUp); // saving data into our database;
				return "SignUp Successfully";
			}

		} catch (Exception e) { // printing exception
			e.printStackTrace();
			return "Email already Used";

		}

	}

	public String deleteSignUp(SignUp idPass) {
		try {
			// Fetch all sign-ups and find the one with the matching username
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp -> signUp.getName().equals(idPass.getName())).findFirst();
			if (optionalSignUp.isPresent()) {
				// Storing fetched value into SignUp Object
				SignUp signUp = optionalSignUp.get();
				// Comparing Password and email provided by the User
				if (signUp.getPassword().equals(idPass.getPassword())) {
					if (signUp.getEmail().equals(idPass.getEmail())) { // Checking for email
						signUpRepository.delete(signUp);
						return "Account Deleted Successfully";
					} else {
						return "Invalid Email";
					}
				}

				else {
					return "Invalid Password";
				}
			} else {
				return "Invalid Username";
			}

		} catch (Exception e) {
			// log the exception
			e.printStackTrace();
			return "Invalid Credential";

		}

	}

	public String login(Login login) {
		try {
			// Fetch all sign-ups and find the one with the matching username
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp -> signUp.getName().equals(login.getUsername())).findFirst();

			// Check if the username exists
			if (optionalSignUp.isPresent()) {
				SignUp signUp = optionalSignUp.get();
				// Check if the password matches
				if (signUp.getPassword().equals(login.getPassword()) || signUp.getEmail().equals(login.getUsername())) {
					return "Login Successfully";
				} else {
					return "Invalid Password";
				}
			} else {
				return "Invalid Username";
			}
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			return "Invalid Credentials";
		}
	}

}
