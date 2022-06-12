package com.demo.code.arbindnegi.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.code.arbindnegi.demo.entities.User;

/**
 * @author Arbind Negi 12-Apr-2022
 *
 */
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findByEmail(String email);
}
