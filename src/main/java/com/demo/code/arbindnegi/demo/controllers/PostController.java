/**
 * 
 */
package com.demo.code.arbindnegi.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.code.arbindnegi.demo.config.Constants;
import com.demo.code.arbindnegi.demo.dto.ApiResponse;
import com.demo.code.arbindnegi.demo.dto.PostDto;
import com.demo.code.arbindnegi.demo.dto.PostResponse;
import com.demo.code.arbindnegi.demo.services.PostService;

/**
 * @author Arbind Negi 19-Apr-2022
 *
 */

@RestController
@RequestMapping("/api/")
public class PostController {
    
    @Autowired
    PostService postService;
    

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(
	    @RequestBody PostDto postDto,
	    @PathVariable Integer userId,
	    @PathVariable Integer categoryId) {
	
	PostDto createPost =  this.postService.createPost(postDto, userId, categoryId);
	
	return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
    }
    
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
	List<PostDto> posts = this.postService.getPostByUser(userId);
	
	return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	
    }
    
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
	List<PostDto> posts = this.postService.getPostBycategory(categoryId);
	
	return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	
    }
    
  @GetMapping("/posts")
  public ResponseEntity<PostResponse> getAllPost( 
	  @RequestParam(value = "pageNumber", defaultValue = Constants.PAGE_NUMBER, required = false) Integer pageNumber, 
	  @RequestParam (value = "pageSize", defaultValue = Constants.PAGE_SIZE, required = false) Integer pageSize,
          @RequestParam (value = "sortBy", defaultValue = Constants.SORT_BY, required = false)String sortBy,
          @RequestParam (value = "sortDirection", defaultValue = Constants.SORT_DIRECTION, required = false)String sortDirection)

  {
      PostResponse postResponse =  this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
      
      return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
      
  }
  
  @GetMapping("/posts/{postId}")
  public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
      PostDto postDto =  this.postService.getPostById(postId);
      
      return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
  }
  
  @DeleteMapping("/posts/{postId}")
  public ApiResponse deletePost(@PathVariable Integer postId) {
      postService.deletePost(postId);
      
      return new ApiResponse("Post is successfully delted !!", true);
      
  }

  @PutMapping("/posts/{postId}")
  public ResponseEntity<PostDto> updatePost(
	  @RequestBody PostDto postDto,
	  @PathVariable Integer postId) {
      
     PostDto updatedPostDto =  postService.updatePost(postDto, postId);
     return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
      
  }
 
  @GetMapping("/posts/search/{searchKeyWord}")
  public ResponseEntity<List<PostDto>> searchPostBytitle(
	  @PathVariable ("searchKeyWord") String searchKeyWord) {
      
      List<PostDto> searchPost = postService.searchPosts(searchKeyWord);
      
      return new ResponseEntity<List<PostDto>>(searchPost, HttpStatus.OK);
  }
  
}
