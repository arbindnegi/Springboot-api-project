/**
 * 
 */
package com.demo.code.arbindnegi.demo.services;

import java.util.List;

import com.demo.code.arbindnegi.demo.dto.UserDto;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */
public interface UserService {
    
    UserDto registerNewUser(UserDto user); // This method is use to create a user with encrypted password and the roles
    
    UserDto createUser(UserDto user); // This method is use to create a normal user
    UserDto updateUser(UserDto user, Integer id);
    UserDto getUserById( Integer id);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
