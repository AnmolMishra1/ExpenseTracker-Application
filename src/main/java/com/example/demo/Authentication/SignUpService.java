package com.example.demo.Authentication;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class SignUpService {
	@Autowired
	SignUpRepository signUpRepository; // creating object of signUp Service using SpringBoot Autowired;
// SignUp Logic

	public String addSignUp(SignUp signUp, HttpServletRequest request) { // this is for singUp
		try {
			// fetch existing data
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp1 -> signUp1.getEmail().equals(signUp.getEmail())
							|| signUp1.getUsername().equals(signUp.getUsername()))
					.findFirst();

			if (optionalSignUp.isPresent()) {// checking email already used or not
				return "User Already Existed";
			} else {
				signUpRepository.save(signUp);
				HttpSession session = request.getSession(true);
				session.setAttribute("userId", signUp.getId()); // saving data into our database;
				return "SignUp Successfully";
			}

		} catch (Exception e) { // printing exception
			e.printStackTrace();
			return "Bad Credential";

		}

	}

	// DeleteAccount Logic;
	public String deleteSignUp(SignUp idPass) {
		try {
			// Fetch all sign-ups and find the one with the matching userName
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp -> signUp.getUsername().equals(idPass.getUsername())).findFirst();
			if (optionalSignUp.isPresent()) {
				// Storing fetched value into SignUp Object
				SignUp signUp = optionalSignUp.get();
				// Comparing Password and email provided by the User
				if (signUp.getPassword().equals(idPass.getPassword())) {
					if (signUp.getEmail().equals(idPass.getEmail())) { // Checking for email
						signUpRepository.delete(signUp);
						return "Account Deleted Successfully";
					} else {
						return "Invalid Email"; // for invalid Email
					}
				}

				else {
					return "Invalid Password"; // this is for Invalid Password
				}
			} else {
				return "User Does Not Exist"; // for invalid UserName
			}

		} catch (Exception e) {
			// log the exception
			e.printStackTrace();
			return "Invalid Credential";

		}

	}

	// ChangePassword Logic
	public String changePassword(ChangePassword password) {

		// Fetching data from Database
		try {
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp -> signUp.getName().equals(password.getUsername())
							|| signUp.getEmail().equals(password.getUsername()))
					.findFirst();

			// check if userName exists;

			if (optionalSignUp.isPresent()) {
				SignUp signUp = optionalSignUp.get();
				if (signUp.getPassword().equals(password.getPassword())) { // Checking for password
					if (signUp.getPassword().equals(password.getNewPassword())) { // Checking if new password is same as
																					// old password
						return "Error:Same as Previous Password";
					} else {
						signUp.setPassword(password.getNewPassword()); // Updating password
						signUpRepository.save(signUp); // Saving into Database;
						return "Password Changed Successfully";
					}
				} else {
					return "Invalid Password";
				}
			} else
				return "User does not exist";

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "Bad Credential";
	}

	// Login Logic
	public String login(Login login, HttpServletRequest request) {
		try {
			// Fetch all sign-ups and find the one with the matching userName
			Optional<SignUp> optionalSignUp = ((Collection<SignUp>) signUpRepository.findAll()).stream()
					.filter(signUp -> signUp.getUsername().equals(login.getUsername())
							|| signUp.getEmail().equals(login.getUsername()))
					.findFirst();
			// Check if the userName exists
			if (optionalSignUp.isPresent()) {
				SignUp signUp = optionalSignUp.get();
				// Check if the password matches
				if (signUp.getPassword().equals(login.getPassword())) {
					HttpSession session = request.getSession(true);
					session.setAttribute("userId", signUp.getId());
					return "Login Successfully";
				} else {
					return "Invalid Password";
				}
			} else {
				return "User Does not Exist";
			}
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			return "Invalid Credentials";
		}
	}
	// Logout Logic

	public String logout(HttpServletRequest request) {
		try {
			// Invalidate the current session to log out the user
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
				return "Logout Successfully";
			} else {
				return "Login First";
			}
		} catch (Exception e) {
			// Log the exception
			e.printStackTrace();
			return "Logout Failed";
		}
	}
}
