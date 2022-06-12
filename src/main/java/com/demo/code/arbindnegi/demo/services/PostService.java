/**
 * 
 */
package com.demo.code.arbindnegi.demo.services;

import java.util.List;

import com.demo.code.arbindnegi.demo.dto.PostDto;
import com.demo.code.arbindnegi.demo.dto.PostResponse;

/**
 * @author Arbind Negi 19-Apr-2022
 *
 */
public interface PostService {

   PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
   PostDto updatePost(PostDto postDto, Integer postId);
   void deletePost(Integer postId);
    
   PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection);
   PostDto getPostById(Integer postId);
   List<PostDto> getPostBycategory(Integer categoryId);
   List<PostDto> getPostByUser(Integer userId);
   List<PostDto> searchPosts(String searchKeyWord);
}
