/**
 * 
 */
package com.demo.code.arbindnegi.demo.services;

import java.util.List;

import com.demo.code.arbindnegi.demo.dto.CategoryDto;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */
public interface CategoryService {
    
    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    public void deleteCategory(Integer categoryId);
    public CategoryDto getCategory(Integer categoryId);
    public List<CategoryDto> getAllCategories();
}
