/**
 * 
 */
package com.demo.code.arbindnegi.demo.exceptions;

/**
 * @author Arbind Negi 29-May-2022
 *
 */
public class ApiExceptionHanddler extends RuntimeException{

    /**
     * @param message
     */
    public ApiExceptionHanddler(String message) {
	super(message);
	
    }

    /**
     * 
     */
    public ApiExceptionHanddler() {
	super();
	
    }

    
    
}
