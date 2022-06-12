/**
 * 
 */
package com.demo.code.arbindnegi.demo.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.code.arbindnegi.demo.dto.PostDto;
import com.demo.code.arbindnegi.demo.dto.PostResponse;
import com.demo.code.arbindnegi.demo.entities.Category;
import com.demo.code.arbindnegi.demo.entities.Post;
import com.demo.code.arbindnegi.demo.entities.User;
import com.demo.code.arbindnegi.demo.exceptions.ResourceNotFoundException;
import com.demo.code.arbindnegi.demo.repositories.CategoryRepository;
import com.demo.code.arbindnegi.demo.repositories.PostRepository;
import com.demo.code.arbindnegi.demo.repositories.UserRepository;
import com.demo.code.arbindnegi.demo.services.PostService;

/**
 * @author Arbind Negi 19-Apr-2022
 *
 */

@Service
public class PostSeviceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    
    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
	User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", " User id ", userId));
	Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", " Category id ", categoryId));
	
	Post post = this.modelMapper.map(postDto, Post.class);
	post.setImageName("defaultName.png");
	post.setAddedDate(new Date());
	post.setUser(user);
	post.setCategory(category);
	
	Post newPost =  this.postRepository.save(post);
	return this.modelMapper.map(newPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
	Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "Post Id" ,postId));
	post.setTitle(postDto.getTitle());
	post.setContent(postDto.getContent());
	post.setImageName(postDto.getImageName());
	Post updatdedPost = postRepository.save(post);
	return modelMapper.map(updatdedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
	Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post" , "Post Id" ,postId));
	postRepository.delete(post);
	
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
	
	Sort sort = (sortDirection.equalsIgnoreCase("ASC")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
	/*if(sortDirection.equalsIgnoreCase("ASC")) {
	    sort =  Sort.by(sortBy).ascending();
	} else {
	    sort =  Sort.by(sortBy).descending();
	}*/
	    
	Pageable page = PageRequest.of(pageNumber, pageSize, sort);
	Page<Post> allPagePost = this.postRepository.findAll(page);
	List<Post> allPost = allPagePost.getContent();
	
	List<PostDto> allPostDto =  allPost.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
	PostResponse postResponse = new PostResponse();
	postResponse.setContent(allPostDto);
	postResponse.setPageNumber(allPagePost.getNumber());
	postResponse.setPageSize(allPagePost.getSize());
	postResponse.setTotalElement(allPagePost.getTotalElements());
	postResponse.setTotalPages(allPagePost.getTotalPages());
	postResponse.setLastPage(allPagePost.isLast());
	
	return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
	Post post = this.postRepository.findById(postId)
                             .orElseThrow(() -> new ResourceNotFoundException("Post" , "Post Id" ,postId));
	
	return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostBycategory(Integer categoryId) {

	Category category = this.categoryRepository.findById(categoryId)
		.orElseThrow(() -> new ResourceNotFoundException("Category" , "Category Id" ,categoryId));
	
	List<Post> postList = this.postRepository.findPostByCategory(category);
	List<PostDto> listPostDto = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
		                    .collect(Collectors.toList());
	
	return listPostDto;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
	User user = this.userRepository.findById(userId)
		   .orElseThrow(() -> new ResourceNotFoundException("User" , "User Id" ,userId));
	
	List<Post> postList = this.postRepository.findPostByUser(user);
	
	List<PostDto> listPostDto = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
	
	return listPostDto;
    }

    @Override
    public List<PostDto> searchPosts(String searchKeyWord) {
	List<Post> posts = this.postRepository.searchByTitleContains("%"+searchKeyWord+"%");
	List<PostDto> poostDto = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class))
        .collect(Collectors.toList());
	
	return poostDto;
    }

}
