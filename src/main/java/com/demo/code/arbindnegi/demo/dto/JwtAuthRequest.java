/**
 * 
 */
package com.demo.code.arbindnegi.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Arbind Negi 21-May-2022
 *
 */

@Data
public class JwtAuthRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    
    private String username; // Email is using as a username
    private String password;
}
