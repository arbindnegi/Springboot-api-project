/**
 * 
 */
package com.demo.code.arbindnegi.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

/**
 * @author Arbind Negi 21-May-2022
 *
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    @Autowired
    private UserDetailsService userDetailsService;
   
    @Autowired
    private JwtTokkenHelper jwtTokkenHelper;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException {
	
	String requestHeader = request.getHeader("Authorization");
	String userName = null;
	String token =null;
	// JWT Token is in the form "Bearer token". Remove Bearer word and get
       // only the Token
	//System.out.println("------>" +request);
	if (requestHeader != null && requestHeader.startsWith("Bearer")) {

	    // Bearer 1234555asas
	    // Get the substring with 'Bearer' + space
	    token = requestHeader.substring(7);

	    try {
		userName = jwtTokkenHelper.getUserNameFromToken(token);
	    } catch (IllegalArgumentException ex) {
		System.out.println("Unable to get JWT token!!");
	    } catch (ExpiredJwtException ex) {
		System.out.println("Jwt token has Expired!!");
	    } catch (MalformedJwtException ex) {
		System.out.println("Invalid JWT token!!");
	    }

	} else {
	    System.out.println("JWT Token does not begin with Bearer String");
	}
	
	if (userName != null 
		&& SecurityContextHolder.getContext().getAuthentication() == null) {
	    
	    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
	    
	 // if token is valid configure Spring Security to manually set authentication
	    
	    if(this.jwtTokkenHelper.validateToken(token, userDetails)) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		
		/*After setting the Authentication in the context, we specify
		  that the current user is authenticated. So it passes the
		  Spring Security Configurations successfully
		*/
		
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    } 
	    else {
		System.out.println("Invalid jwt token!!");
	    }
	}
	else {
	    System.out.println("User name is Null or Context is null");
	}
	
	filterChain.doFilter(request, response);
    }

}
