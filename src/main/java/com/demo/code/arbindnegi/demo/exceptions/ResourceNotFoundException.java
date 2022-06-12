/**
 * 
 */
package com.demo.code.arbindnegi.demo.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName;
    private String filedName;
    private long fieldvalue;
    private String fieldvalueForString;
    /**
     * @param resourceName
     * @param filedName
     * @param fieldvalue
     */
    // With the help of : %s, we will able to represent the Long value also
    public ResourceNotFoundException(String resourceName, String filedName, long fieldvalue) {
	super(String.format("%s not found with %s : %s", resourceName,filedName,fieldvalue));
	this.resourceName = resourceName;
	this.filedName = filedName;
	this.fieldvalue = fieldvalue;
    }
    
    public ResourceNotFoundException(String resourceName, String filedName, String fieldvalue) {
	super(String.format("%s not found with %s : %s", resourceName,filedName,fieldvalue));
	this.resourceName = resourceName;
	this.filedName = filedName;
	this.fieldvalueForString = fieldvalue;
    }    
    
}
