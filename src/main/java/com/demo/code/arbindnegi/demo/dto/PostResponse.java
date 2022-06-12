/**
 * 
 */
package com.demo.code.arbindnegi.demo.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbind Negi 23-Apr-2022
 *
 */

@NoArgsConstructor
@Setter
@Getter
public class PostResponse {
    private List<PostDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElement;
    private int totalPages;
    private boolean lastPage;
    

}
