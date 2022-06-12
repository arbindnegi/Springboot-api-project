/**
 * 
 */
package com.demo.code.arbindnegi.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbind Negi 07-May-2022
 *
 */

@Entity
@Table(name="comments")
@NoArgsConstructor
@Setter
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String content;
    
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
    
    
    
}
