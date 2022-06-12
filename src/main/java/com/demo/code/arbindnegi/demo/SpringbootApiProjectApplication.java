package com.demo.code.arbindnegi.demo;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.code.arbindnegi.demo.config.Constants;
import com.demo.code.arbindnegi.demo.entities.Role;
import com.demo.code.arbindnegi.demo.repositories.RoleRepository;

@SpringBootApplication
public class SpringbootApiProjectApplication implements CommandLineRunner {

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootApiProjectApplication.class, args);
	}
	
	//This bean is created to autowired ModelMapper object in my project to convert or Map the Object automatically to other object
	@Bean
	public ModelMapper modelmapperBean() {
	    
	    return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
	  
	    Role roleAdmin = new Role();
	    roleAdmin.setId(Constants.ADMINL_USER);
	    roleAdmin.setName("ADMINL_USER");
	    
	    Role roleUser = new Role();
	    roleUser.setId(Constants.NORMAL_USER);
	    roleUser.setName("NORMAL_USER");
	    
	    List<Role> roles = new ArrayList<>();
	    roles.add(roleAdmin);
	    roles.add(roleUser);
	    
	    List<Role> saveRoles  = roleRepository.saveAll(roles);
	    saveRoles.forEach(result -> {
		System.out.println(result.getName());
	    });
	}
	
}
