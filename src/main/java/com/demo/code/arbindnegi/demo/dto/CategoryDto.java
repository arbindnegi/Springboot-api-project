/**
 * 
 */
package com.demo.code.arbindnegi.demo.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Integer categoryId;
    
    @NotEmpty
    private String categoryTitle;
    
    @NotEmpty
    private String categoryDescription;
}
