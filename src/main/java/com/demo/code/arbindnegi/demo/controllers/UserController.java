package com.demo.code.arbindnegi.demo.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.code.arbindnegi.demo.dto.ApiResponse;
import com.demo.code.arbindnegi.demo.dto.UserDto;
import com.demo.code.arbindnegi.demo.services.UserService;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * Create user by using POST Mapping
     */

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser (@Valid @RequestBody UserDto userDto) {
	UserDto createUserDto = this.userService.createUser(userDto);
	
	return new ResponseEntity<UserDto>(createUserDto, HttpStatus.CREATED);
	
    }
 
    /**
     * Update user by using PUT Mapping with @PathVariable
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer id) {
	UserDto updatedUserDto = this.userService.updateUser(userDto, id);
	return ResponseEntity.ok(updatedUserDto);
    }
    
    /**
     * Delete user by using DELETE Mapping with @PathVariable
     * Only ADMIN will able to delete the users. @EnableGlobalMethodSecurity used in SecurityConfiguration class level
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser (@RequestBody UserDto userDto, @PathVariable("userId") Integer id){
	
	this.userService.deleteUser(id);
	return new ResponseEntity<ApiResponse>(new ApiResponse("User is deleted successfully", true), HttpStatus.OK);
    }
    
    /**
     * Get All users by using GET Mapping
     */
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
	
	return ResponseEntity.ok(this.userService.getAllUsers());
	
    }
    
    
    /**
     * Get user by Id using GET Mapping with @PathVariable
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser( @PathVariable Integer userId) {
	
	return ResponseEntity.ok(this.userService.getUserById(userId));
	
    }
}
