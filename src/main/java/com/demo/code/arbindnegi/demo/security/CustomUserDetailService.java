/**
 * 
 */
package com.demo.code.arbindnegi.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.code.arbindnegi.demo.entities.User;
import com.demo.code.arbindnegi.demo.exceptions.ResourceNotFoundException;
import com.demo.code.arbindnegi.demo.repositories.UserRepository;

/**
 * @author Arbind Negi 15-May-2022
 *
 */

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// Loading the user from database by user name
	User user = this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", " Email id: ", username));
	
	return user;
    }

}
