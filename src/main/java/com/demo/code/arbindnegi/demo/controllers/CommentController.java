/**
 * 
 */
package com.demo.code.arbindnegi.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.code.arbindnegi.demo.dto.ApiResponse;
import com.demo.code.arbindnegi.demo.dto.CommentDto;
import com.demo.code.arbindnegi.demo.services.CommentService;

/**
 * @author Arbind Negi 07-May-2022
 *
 */

@RestController
@RequestMapping("/api/")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
	    @RequestBody CommentDto commentDto,
	    @PathVariable Integer postId) {
	
	CommentDto createComment = commentService.createComment(commentDto, postId);
	
	return new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComments(@PathVariable Integer commentId) {
	
	commentService.delete(commentId);
	
	return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted Successfully", true), HttpStatus.OK);
    }
}
