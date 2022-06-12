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
public class JwtAuthResponse implements Serializable {
    
    private static final long serialVersionUID = -8091879091924046844L;
    
    private String token;

}
