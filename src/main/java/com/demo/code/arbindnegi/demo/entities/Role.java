/**
 * 
 */
package com.demo.code.arbindnegi.demo.entities;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author Arbind Negi 15-May-2022
 *
 */

@Entity
@Data 
public class Role {
    
    @Id
    private int id;
    
    private String name;
    
    

}
