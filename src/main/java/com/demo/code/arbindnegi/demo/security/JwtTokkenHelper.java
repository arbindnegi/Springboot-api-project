/**
 * 
 */
package com.demo.code.arbindnegi.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Arbind Negi 21-May-2022
 *
 */

@Component
public class JwtTokkenHelper {
    
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    
    //@Value("${jwt.secret}")
    private String secret = "jwtTokenKey";
    
    //get the user name from the JWT token
    public String getUserNameFromToken (String token) {
	
	return getClaimFromToken(token, Claims:: getSubject);
    }
    
    //get the Expiration Date from the JWT token
    public Date getExpirationDatefromToken(String token) {
	
	return getClaimFromToken(token, Claims:: getExpiration);
    }
    
    public  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	final Claims claims = getAllClaimsFromToken(token);
	
	return claimsResolver.apply(claims);
    }
    
    private Claims getAllClaimsFromToken(String token) {
	
	return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
	final Date expirationDate = getExpirationDatefromToken(token);
	
	return expirationDate.before(new Date());
    }
    
    public String genarateToken(UserDetails userDetails) {
	Map<String, Object> claims = new HashMap<>();
	
	return doGenerateToken(claims, userDetails.getUsername());
    }
    
    private String doGenerateToken(Map<String, Object> claims, String subject) {
	final Date createdDate = new Date();
	final Date expirationDate = new Date(createdDate.getTime() + JWT_TOKEN_VALIDITY * 1000);
	  
	return Jwts.builder()
		.setClaims(claims)
		.setSubject(subject)
		.setIssuedAt(createdDate)
		.setExpiration(expirationDate)
		.signWith(SignatureAlgorithm.HS512, secret)
		.compact();
    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
	final String userName = getUserNameFromToken(token);
	
	return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
