package com.assignment.funix.assignment01.dao;

import com.assignment.funix.assignment01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
