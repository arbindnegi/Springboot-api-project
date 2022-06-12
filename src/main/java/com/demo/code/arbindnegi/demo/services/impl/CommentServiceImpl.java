/**
 * 
 */
package com.demo.code.arbindnegi.demo.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.code.arbindnegi.demo.dto.CommentDto;
import com.demo.code.arbindnegi.demo.entities.Comment;
import com.demo.code.arbindnegi.demo.entities.Post;
import com.demo.code.arbindnegi.demo.exceptions.ResourceNotFoundException;
import com.demo.code.arbindnegi.demo.repositories.CommentRepository;
import com.demo.code.arbindnegi.demo.repositories.PostRepository;
import com.demo.code.arbindnegi.demo.services.CommentService;

/**
 * @author Arbind Negi 07-May-2022
 *
 */

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
	Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "Post Id" ,postId));
	Comment comment = modelMapper.map(commentDto, Comment.class);
	comment.setPost(post);
	
	Comment savedComment = commentRepository.save(comment);
	
	return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void delete(Integer commentId) {
	Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment" , "Comment Id" ,commentId));
	
	commentRepository.delete(comment);
    }

}
