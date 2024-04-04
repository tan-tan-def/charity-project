package com.assignment.funix.assignment01.service.user;

import com.assignment.funix.assignment01.dao.UserRepository;
import com.assignment.funix.assignment01.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        User user = null;
        if(result.isPresent()){
            user = result.get();
        }else{
            throw new RuntimeException("Did not find the User id "+theId);
        }
        return user;
    }
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

}
