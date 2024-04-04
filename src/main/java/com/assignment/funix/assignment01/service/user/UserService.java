package com.assignment.funix.assignment01.service.user;

import com.assignment.funix.assignment01.entity.Donation;
import com.assignment.funix.assignment01.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(int theId);
    User saveUser(User user);
    void deleteById(int theId);

}
