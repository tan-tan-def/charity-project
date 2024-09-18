package com.assignment.funix.assignment01.controller;

import com.assignment.funix.assignment01.date.Date;
import com.assignment.funix.assignment01.entity.Donation;
import com.assignment.funix.assignment01.entity.UserDonation;
import com.assignment.funix.assignment01.service.donation.DonationService;
import com.assignment.funix.assignment01.service.user_donation.UserDonationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {
    private DonationService donationService;
    private UserDonationService userDonationService;
    @Autowired
    public DonationController(DonationService donationService, UserDonationService userDonationService) {
        this.userDonationService = userDonationService;
        this.donationService = donationService;
    }
    @GetMapping("/main-page")
    public String mainPage(){
        return "admin/home";
    }
    @RequestMapping("/home")
    public String donation(Model theModel, HttpSession session){
        theModel.addAttribute("newDonation", new Donation());
        List<Donation> donations = donationService.findAll();
        theModel.addAttribute("donations",donations);
        theModel.addAttribute("size", donations.size());
        //Show swal
        Boolean msg = (Boolean) session.getAttribute("msg");
        if(msg!=null){
            theModel.addAttribute("msg", msg);
            session.removeAttribute("msg");
        }
        return "admin/donation";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute("newDonation") Donation donation, HttpSession session){
        //set up created
        String formattedDateTime = Date.date();
        donation.setCreated(formattedDateTime);
        //set up status
        donation.setStatus(0);
        //set up money
        donation.setMoney(0);
        donationService.saveDonation(donation);
        //show swal
        session.setAttribute("msg", true);
        return "redirect:/donation/home";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("idDelete") int id){
        Donation donation = donationService.findById(id);
        donation.setStatus(4);
        donationService.saveDonation(donation);
        return "redirect:/donation/home";
    }
    @PostMapping("/update")
    public String update(@RequestParam("idUpdate") int id,
                         @RequestParam("code") String code,
                         @RequestParam("name") String name,
                         @RequestParam("startDate") String startDate,
                         @RequestParam("endDate") String endDate,
                         @RequestParam("organizationName") String organizationName,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("description") String description
    ){
        Donation donation = donationService.findById(id);
        donation.setCode(code);
        donation.setName(name);
        donation.setStartDate(startDate);
        donation.setEndDate(endDate);
        donation.setOrganizationName(organizationName);
        donation.setPhoneNumber(phoneNumber);
        donation.setDescription(description);
        donationService.saveDonation(donation);
        return "redirect:/donation/home";
    }
    @GetMapping("/detail")
    public String detail(@RequestParam("idDetail") int idDetail, Model theModel) {
        Donation donation = donationService.findById(idDetail);
        List<UserDonation> userDonations = userDonationService.findAll();
        theModel.addAttribute("donationDetail", donation);
        theModel.addAttribute("userDonationList",userDonations.size());
        theModel.addAttribute("userDonations",userDonations);
        return "admin/detail";
    }
//    Button "Xác nhận"
    @GetMapping("/confirm")
    public String confirm(@RequestParam("id") int id, @RequestParam("idDetail") int idDetail, Model theModel){
        //Set up the status of the donation person
        UserDonation userDonation = userDonationService.findById(id);
        userDonation.setStatus(1);
        userDonationService.saveUserDonation(userDonation);
        //Save total money after donated
        Donation donation = donationService.findById(idDetail);
        int currentAmount = donation.getMoney();
        donation.setMoney(currentAmount+userDonation.getMoney());
        donationService.saveDonation(donation);
        return "redirect:/donation/detail?idDetail=" + idDetail;
    }
//    Button "Hủy xác nhận"
    @GetMapping("/cancel")
    public String cancel(@RequestParam("id") int id, @RequestParam("idDetail") int idDetail, Model theModel){
        //Set up the status=2 (Hủy Xác nhận) of the donation person
        UserDonation userDonation = userDonationService.findById(id);
        userDonation.setStatus(2);
        userDonationService.saveUserDonation(userDonation);
        return "redirect:/donation/detail?idDetail=" + idDetail;
    }
    @PostMapping("/donate")
    public String donate(@RequestParam("idDonate") int id){
        Donation donation = donationService.findById(id);
        donation.setStatus(1);
        donationService.saveDonation(donation);
        return "redirect:/donation/home";
    }
    @PostMapping("/finish")
    public String finish(@RequestParam("idFinish") int id){
        Donation donation = donationService.findById(id);
        donation.setStatus(2);
        donationService.saveDonation(donation);
        return "redirect:/donation/home";
    }
    @PostMapping("/close")
    public String close(@RequestParam("idClose") int id){
        Donation donation = donationService.findById(id);
        donation.setStatus(3);
        donationService.saveDonation(donation);
        return "redirect:/donation/home";
    }
}
