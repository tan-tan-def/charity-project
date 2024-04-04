package com.assignment.funix.assignment01.controller;

import com.assignment.funix.assignment01.Date.Date;
import com.assignment.funix.assignment01.entity.Donation;
import com.assignment.funix.assignment01.entity.UserDonation;
import com.assignment.funix.assignment01.service.donation.DonationService;
import com.assignment.funix.assignment01.service.user_donation.UserDonationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user-donation")
public class UserDonationController {
    private UserDonationService userDonationService;
    private DonationService donationService;
    @Autowired
    public UserDonationController(UserDonationService userDonationService, DonationService donationService){
        this.userDonationService = userDonationService;
        this.donationService = donationService;
    }
    @RequestMapping("/home")
    public String home(Model theModel, HttpSession session,
                       @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        //Pageable and show List of donation
        Page<Donation> donations = donationService.findAll(pageNo);
        theModel.addAttribute("totalPages", donations.getTotalPages());
        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("donations",donations);
        //Create userDonation for quick donate
        theModel.addAttribute("userDonation", new UserDonation());
        //Show swal
        Boolean msg = (Boolean) session.getAttribute("msg");
        if (msg != null) {
            theModel.addAttribute("msg", msg);
            session.removeAttribute("msg");
        }
        return "public/home";
    }
    @GetMapping("/detail-for-donate")
    public String detailForDonate(@RequestParam("id") int id, Model theModel, HttpSession session){
        //Find the donation to show
        Donation donation =  donationService.findById(id);
        theModel.addAttribute("theDonation", donation);
        //Add user who donated for charity organizations
        theModel.addAttribute("userDonation", new UserDonation());
        //List of donors
        List<UserDonation> userDonationList = userDonationService.findAll();
        theModel.addAttribute("userDonationList",userDonationList);
        //Show swal
        Boolean msg = (Boolean) session.getAttribute("msg");
        if (msg != null) {
            theModel.addAttribute("msg", msg);
            session.removeAttribute("msg");
        }
        return "public/detail-for-donate";
    }
    @PostMapping("/save")
    public String saveUserDonate(@ModelAttribute("userDonation") UserDonation userDonation,
                                 @RequestParam("idDonation") int id,
                                 @RequestParam("idShowPage") String idShowPage,
                                 @RequestParam(name = "idPageable", defaultValue="1") int idPageable,
                                 HttpSession session){
        //set the date which a person donates
        String formattedDateTime = Date.date();
        userDonation.setCreated(formattedDateTime);
        //Set status == 0 (awaiting confirmation) for the person who donated money to charity organizations.
        userDonation.setStatus(0);
        //Set donation_id for userDonation
        Donation donation = donationService.findById(id);
        userDonation.setDonation(donation);
        //Show swal
        session.setAttribute("msg", true);
        //Save userDonation
        userDonationService.saveUserDonation(userDonation);
        if(idShowPage.equals("home")){
            return "redirect:/user-donation/home?pageNo="+ idPageable;
        }
        return "redirect:/user-donation/detail-for-donate?id="+id;
    }
}
