package com.example.Users.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Users.dto.UsersDTO;
import com.example.Users.entity.Users;
import com.example.Users.response.UserResponse;
import com.example.Users.service.UserService;
import com.example.Users.util.Util;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/upload")
	public ResponseEntity<Object> uploadUser(@RequestParam("file") MultipartFile file) {

		List<Users> usersList;
		UserResponse response = new UserResponse();

		try {
			usersList = Util.convertCsvtoList(file.getInputStream());
		} catch (ParseException | IOException e) {
			response.setMessage("Processing Failed");
			return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

		}
		try {
			userService.save(usersList);
		} catch (DataException ex) {
			response.setMessage("Insertion failed");
			return new ResponseEntity<Object>(response, HttpStatus.CREATED);
		}
		response.setMessage("Successfully inserted");
		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@GetMapping("/users")
	public ResponseEntity<Object> getAllUsers(@RequestParam("minSalary") Optional<Double> minSalary,
			@RequestParam("maxSalary") Optional<Double> maxSalary, @RequestParam("order") Optional<String> order,
			@RequestParam("limit") Optional<Integer> limit) {

		List<Users> userList = userService.getAllUsers(minSalary, maxSalary, order, limit);
		UserResponse response = new UserResponse();
		response.setResults(userList);

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@GetMapping("/users/{id}")
	public ResponseEntity<Object> getAllUsers(@PathVariable("id") String id) {

		Users user = userService.getById(id);
		UserResponse response = new UserResponse();
		response.setResults(user);

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}

	@PostMapping("/users/create")
	public ResponseEntity<Object> getAllUsers(@Valid @RequestBody Users user) {

		UserResponse response = new UserResponse();

		Users userExists = userService.findByLoginIdOrId(user.getLoginId(), user.getId());
		if (userExists == null) {
			userService.create(user);
			response.setMessage("Successfully created");
		} else {
			if (userExists.getId().equals(user.getId())) {
				response.setMessage("Employee ID already exists");
			} else {
				response.setMessage("Employee Login not unique");
			}
		}

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
	
	@PutMapping("/users")
	public ResponseEntity<Object> update(@Valid @RequestBody Users users) {

		UserResponse response = new UserResponse();
		Users user = userService.getById(users.getId());
		if(user==null){
			response.setMessage("No such employee");
		} else{
		int rowsUpdated = userService.updateById(users);
		if(rowsUpdated>0){
			response.setMessage("Successfully updated");
		}
	  }

		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
		UserResponse response = new UserResponse();
		Users users = userService.getById(id);
		if(users!=null){
			userService.deleteById(users);
			response.setMessage("Deleted successfully");
		} else{
			response.setMessage("No such employee");
		}


		return new ResponseEntity<Object>(response, HttpStatus.OK);

	}
	
	
	

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
		return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
