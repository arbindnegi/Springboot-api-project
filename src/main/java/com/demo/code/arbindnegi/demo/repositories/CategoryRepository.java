/**
 * 
 */
package com.demo.code.arbindnegi.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.code.arbindnegi.demo.entities.Category;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
