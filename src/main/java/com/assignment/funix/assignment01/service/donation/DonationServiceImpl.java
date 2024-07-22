package com.assignment.funix.assignment01.service.donation;

import com.assignment.funix.assignment01.dao.DonationRepository;
import com.assignment.funix.assignment01.entity.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {
    private DonationRepository donationRepository;
    @Autowired
    public DonationServiceImpl(DonationRepository theDonationRepository){
         donationRepository = theDonationRepository;
    }
    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public Donation findById(int theId) {
        Optional<Donation> result = donationRepository.findById(theId);
        Donation donation = null;
        if(result.isPresent()){
            donation = result.get();
        }else{
            throw new RuntimeException("Did not find the User id "+theId);
        }
        return donation;
    }

    @Override
    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    @Override
    public void deleteById(int theId) {
        donationRepository.deleteById(theId);
    }
    @Override
    public Page<Donation> findAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1,5);
        return donationRepository.findAll(pageable);
    }
}
