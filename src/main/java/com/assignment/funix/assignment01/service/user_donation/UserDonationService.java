package com.assignment.funix.assignment01.service.user_donation;

import com.assignment.funix.assignment01.entity.UserDonation;

import java.util.List;

public interface UserDonationService {
    List<UserDonation> findAll();
    UserDonation findById(int theId);
    UserDonation saveUserDonation(UserDonation userDonation);
    void deleteById(int theId);
}
