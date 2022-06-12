/**
 * 
 */
package com.demo.code.arbindnegi.demo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.demo.code.arbindnegi.demo.exceptions.ResourceNotFoundException;
import com.demo.code.arbindnegi.demo.config.Constants;
import com.demo.code.arbindnegi.demo.dto.UserDto;
import com.demo.code.arbindnegi.demo.entities.Role;
import com.demo.code.arbindnegi.demo.entities.User;
import com.demo.code.arbindnegi.demo.repositories.RoleRepository;
import com.demo.code.arbindnegi.demo.repositories.UserRepository;
import com.demo.code.arbindnegi.demo.services.UserService;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDto createUser(UserDto userDto) {
	User user = this.userDtoToUser(userDto);
	
	return this.userToUserDto(userRepo.save(user));
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) { 
	User user = this.userRepo.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
	
	user.setUsername(userDto.getUsername());
	user.setEmail(userDto.getEmail());
	user.setDescription(userDto.getDescription());
	user.setPassword(userDto.getUserPassword());
	
	User updatedUser = this.userRepo.save(user);
	UserDto userconvertToDto = this.userToUserDto(updatedUser);
	return userconvertToDto;
    }

    @Override
    public UserDto getUserById(Integer userId) {
	User user = this.userRepo.findById (userId)
		.orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
	
	return this.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
	
	List<User> users= this.userRepo.findAll();
	List<UserDto> usersDto=  users
		.stream()
		.map(user -> this.userToUserDto(user))
		.collect(Collectors.toList());
	
	return usersDto;
    }

    @Override
    public void deleteUser(Integer userId) {
	User user = this.userRepo.findById (userId)
	            .orElseThrow(()-> new ResourceNotFoundException("User", " id ", userId));
	
	this.userRepo.delete(user);
    }
    
    public User userDtoToUser(UserDto userDto) {
	User user = new User();
	user.setId(userDto.getId());
	user.setUsername(userDto.getUsername());
	user.setPassword(userDto.getUserPassword());
	user.setEmail(userDto.getEmail());
	user.setDescription(userDto.getDescription());
	
	User ModelMapper = this.modelMapper.map(userDto, User.class);
	return ModelMapper;
    }

    public UserDto userToUserDto(User user) {
	UserDto userDto = new UserDto();
	userDto.setId(user.getId());
	userDto.setUsername(user.getUsername());
	userDto.setUserPassword(user.getPassword());
	userDto.setEmail(user.getEmail());
	userDto.setDescription(user.getDescription());
	UserDto userDtoModelMapper = this.modelMapper.map(user, UserDto.class);
	return userDtoModelMapper;
    }

    @Override
    public UserDto registerNewUser(UserDto userDto) {
	User user = this.modelMapper.map(userDto, User.class);
	
	//Encoded user password
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
	//Find Role from Repository
	Role findRoleById = roleRepository.findById(Constants.NORMAL_USER).get();
	user.getRoles().add(findRoleById);
	User saveUser =  userRepo.save(user);
	
	return this.modelMapper.map(saveUser, UserDto.class);
    }
    
    
}
