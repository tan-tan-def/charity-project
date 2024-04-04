package com.assignment.funix.assignment01.service.donation;

import com.assignment.funix.assignment01.entity.Donation;
import org.springframework.data.domain.Page;

import java.util.List;
public interface DonationService {
    List<Donation> findAll();
    Donation findById(int theId);
    Donation saveDonation(Donation donation);
    void deleteById(int theId);
    Page<Donation> findAll(Integer pageNo);

}
