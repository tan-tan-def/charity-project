package com.assignment.funix.assignment01.dao;

import com.assignment.funix.assignment01.entity.UserDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {
}
