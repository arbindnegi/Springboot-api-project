/**
 * 
 */
package com.demo.code.arbindnegi.demo.controllers;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.demo.code.arbindnegi.demo.dto.ApiResponse;
import com.demo.code.arbindnegi.demo.dto.CategoryDto;
import com.demo.code.arbindnegi.demo.dto.UserDto;
import com.demo.code.arbindnegi.demo.services.CategoryService;
import com.demo.code.arbindnegi.demo.services.UserService;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    
    /**
     * Create Category by using POST Mapping
     */

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory (@Valid @RequestBody CategoryDto categoryDto) {
	CategoryDto createcategoryDto = this.categoryService.createCategory(categoryDto);
	
	return new ResponseEntity<CategoryDto>(createcategoryDto, HttpStatus.CREATED);
	
    }
    
    /**
     * Update category by using PUT Mapping with @PathVariable
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer CateId) {
	CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, CateId);
	return new ResponseEntity<CategoryDto>(updatedCategoryDto, HttpStatus.OK);
    }
    
    /**
     * Delete Category by using DELETE Mapping with @PathVariable
     */
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Integer catId){
	
	this.categoryService.deleteCategory(catId);
	return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully", true), HttpStatus.OK);
    }
    
    
    /**
     * Get All Categories by using GET Mapping
     */
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
	
	return ResponseEntity.ok(this.categoryService.getAllCategories());
	
    }
    
    
    /**
     * Get Category by Id using GET Mapping with @PathVariable
     */
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById( @PathVariable Integer categoryId) {
	CategoryDto categoryDto = this.categoryService.getCategory(categoryId);
	
	return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }
}
