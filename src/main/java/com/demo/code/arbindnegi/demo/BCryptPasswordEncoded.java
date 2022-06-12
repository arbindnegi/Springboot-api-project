/**
 * 
 */
package com.demo.code.arbindnegi.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Arbind Negi 29-May-2022
 *
 */
public class BCryptPasswordEncoded {

    /**
     * @param args
     */
    public static void main(String[] args) {
	String password = "xyz123";
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	System.out.println(passwordEncoder.encode(password));
    }

}
