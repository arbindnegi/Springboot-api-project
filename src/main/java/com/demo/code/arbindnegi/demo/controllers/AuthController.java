/**
 * 
 */
package com.demo.code.arbindnegi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.code.arbindnegi.demo.dto.JwtAuthRequest;
import com.demo.code.arbindnegi.demo.dto.JwtAuthResponse;
import com.demo.code.arbindnegi.demo.dto.UserDto;
import com.demo.code.arbindnegi.demo.exceptions.ApiExceptionHanddler;
import com.demo.code.arbindnegi.demo.security.JwtTokkenHelper;
import com.demo.code.arbindnegi.demo.services.UserService;

/**
 * @author Arbind Negi 21-May-2022
 *
 */

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    
    @Autowired
    private JwtTokkenHelper jwtTokkenHelper;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
	    @RequestBody JwtAuthRequest request) throws Exception {
	
	authenticate(request.getUsername(), request.getPassword());
      /*  final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                	request.getUsername(),
                	request.getPassword()
                )
        ); */
        
        //SecurityContextHolder.getContext().setAuthentication(authentication);
        
	UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
	String token = this.jwtTokkenHelper.genarateToken(userDetails);
	
	JwtAuthResponse response = new JwtAuthResponse();
	response.setToken(token);
	
	return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }
    
    private void authenticate(String username, String password) throws Exception {
	
	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
	try {
	    authenticationManager.authenticate(authenticationToken);
	}
	catch(BadCredentialsException be) {
	       
	    throw new ApiExceptionHanddler("Invalid user OR password details !!");
	}
	
    }
    
    @PostMapping("/register")
    private ResponseEntity<UserDto> registerNewUser (@RequestBody UserDto userdto) {
	
	UserDto newRegisterUser = this.userService.registerNewUser(userdto);
	
	return new ResponseEntity<UserDto>(newRegisterUser, HttpStatus.CREATED);
    }

}
