package com.assignment.funix.assignment01.service.user_donation;

import com.assignment.funix.assignment01.dao.UserDonationRepository;
import com.assignment.funix.assignment01.entity.UserDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDonationServiceImpl implements UserDonationService {
    private UserDonationRepository userDonationRepository;
    @Autowired
    public UserDonationServiceImpl(UserDonationRepository userDonationRepository){
        this.userDonationRepository = userDonationRepository;
    }
    @Override
    public List<UserDonation> findAll() {
        return userDonationRepository.findAll();
    }

    @Override
    public UserDonation findById(int theId) {
        Optional<UserDonation> result = userDonationRepository.findById(theId);
        UserDonation userDonation = null;
        if(result.isPresent()){
            userDonation = result.get();
        }else{
            throw new RuntimeException("Did not find the User id "+theId);
        }
        return userDonation;
    }

    @Override
    public UserDonation saveUserDonation(UserDonation userDonation) {
        return userDonationRepository.save(userDonation);
    }

    @Override
    public void deleteById(int theId) {
        userDonationRepository.deleteById(theId);
    }
}
