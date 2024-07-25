package com.example.demo.Authentication;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SignUpRepository extends CrudRepository<SignUp, Long> { // Inheriting CrudRepository to create SignUp
																			// Database;
	Optional<SignUp> findById(Long id);

	public static void main(String[] args) {

	}
}
