/**
 * 
 */
package com.demo.code.arbindnegi.demo.services;

import com.demo.code.arbindnegi.demo.dto.CommentDto;

/**
 * @author Arbind Negi 07-May-2022
 *
 */
public interface CommentService {
    
    CommentDto createComment(CommentDto commentDto, Integer postId);
    void delete(Integer commentId);

}
