/**
 * 
 */
package com.demo.code.arbindnegi.demo.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbind Negi 19-Apr-2022
 *
 */

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
    
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    
    private CategoryDto category;
    private UserDto user;
    
    private Set<CommentDto> comments = new HashSet<>();

}
