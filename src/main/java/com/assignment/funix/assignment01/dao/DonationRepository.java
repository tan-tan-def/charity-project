package com.assignment.funix.assignment01.dao;

import com.assignment.funix.assignment01.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
