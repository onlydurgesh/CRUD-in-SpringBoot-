package com.durgesh.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.payloads.UserDto;
import com.durgesh.repository.UserRepo;
import com.durgesh.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userRervice;
	@Autowired
	private UserRepo userRepo;

	// post method
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody UserDto dto) {
		String email = userRepo.findByEmail(dto.getEmail());
		if (email != null)
			return new ResponseEntity<>("Email already exist ", HttpStatus.OK);
		return new ResponseEntity<>(userRervice.save(dto), HttpStatus.CREATED);
	}

	// Put method
	@PutMapping("/update/{id}")
	ResponseEntity<?>  update(@RequestBody UserDto dto, @PathVariable Long id) {
		return new ResponseEntity<>(this.userRervice.updateUser(dto, id),HttpStatus.OK);
	}

	// Delete Method
	@DeleteMapping("delete/id")
	ResponseEntity<?> deleteByid(@PathVariable Long id) {
		this.userRervice.deleteById(id);
		return new ResponseEntity<>("User Deleted ",HttpStatus.OK);
	}

	@DeleteMapping("delete")
	void deleteAll() {
		this.userRervice.deleteAll();
	}

	// Get Method
	@GetMapping("/get/id")
	ResponseEntity<?>  getUserById(@PathVariable Long id) {
		return new ResponseEntity<>(this.userRervice.findById(id),HttpStatus.OK);
	}

	@GetMapping("/get-all")
	ResponseEntity<?> getAllUser() {
		return new ResponseEntity<>(this.userRervice.findAll(),HttpStatus.OK);
	}

}
