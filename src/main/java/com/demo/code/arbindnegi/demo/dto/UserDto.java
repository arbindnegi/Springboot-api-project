/**
 * 
 */
package com.demo.code.arbindnegi.demo.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.demo.code.arbindnegi.demo.entities.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@NoArgsConstructor
@Setter
@Getter
public class UserDto {

    private int id;
    
    @NotEmpty
    @Size(min = 4, message = "User name should be min 4 characters !!")
    private String username;
    
    @NotEmpty
    @Email (message = "Email is not valid, Please check and correct !!")
    private String email;
    
    @NotEmpty
    @Size(min = 4, max = 12, message =  "Password Must be min of 4 chars and max of 12 chars")
    //@Pattern(regexp = )
    private String userPassword;
    
    @NotEmpty
    private String description;
    
    private Set<RoleDto> roles = new HashSet<>();
}
