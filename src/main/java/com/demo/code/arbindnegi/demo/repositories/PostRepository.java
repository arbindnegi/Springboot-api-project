/**
 * 
 */
package com.demo.code.arbindnegi.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.code.arbindnegi.demo.entities.Category;
import com.demo.code.arbindnegi.demo.entities.Post;
import com.demo.code.arbindnegi.demo.entities.User;
/**
 * @author Arbind Negi 19-Apr-2022
 *
 */
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findPostByUser(User user);
    
    List<Post> findPostByCategory(Category category);
    
    @Query("select p  from Post p where p.title like :key ")
    List<Post> searchByTitleContains(@Param("key") String searchKey); 	
}
