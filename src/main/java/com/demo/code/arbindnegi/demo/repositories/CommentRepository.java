/**
 * 
 */
package com.demo.code.arbindnegi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.code.arbindnegi.demo.entities.Comment;

/**
 * @author Arbind Negi 07-May-2022
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
