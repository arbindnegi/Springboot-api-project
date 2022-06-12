/**
 * 
 */
package com.demo.code.arbindnegi.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.code.arbindnegi.demo.dto.CategoryDto;
import com.demo.code.arbindnegi.demo.entities.Category;
import com.demo.code.arbindnegi.demo.exceptions.ResourceNotFoundException;
import com.demo.code.arbindnegi.demo.repositories.CategoryRepository;
import com.demo.code.arbindnegi.demo.services.CategoryService;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
	Category category = this.modelMapper.map(categoryDto, Category.class);
	Category saveCatogary = this.categoryRepository.save(category);
	
	return this.modelMapper.map(saveCatogary, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> 
	                    new ResourceNotFoundException("Category" , "Category Id" ,categoryId));
	
	category.setCategoryDescription(categoryDto.getCategoryDescription());
	category.setCategoryTitle(categoryDto.getCategoryTitle());
	
	Category updatedCategory = this.categoryRepository.save(category);
	
	return this.modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
	Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> 
        new ResourceNotFoundException("Category" , "CategoryId" ,categoryId));
	
	this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
	Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> 
        new ResourceNotFoundException("Category" , "CategoryId" ,categoryId));
	
	return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
	List<Category> listOfcategories = this.categoryRepository.findAll();
	List<CategoryDto> categoriesDto = listOfcategories.stream()
	                                                   .map(cat -> this.modelMapper.map(cat, CategoryDto.class))
		                                           .collect(Collectors.toList());
	
	return categoriesDto;
    }

}
