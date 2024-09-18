package com.assignment.funix.assignment01.controller;

import com.assignment.funix.assignment01.date.Date;
import com.assignment.funix.assignment01.entity.Role;
import com.assignment.funix.assignment01.entity.User;
import com.assignment.funix.assignment01.service.role.RoleService;
import com.assignment.funix.assignment01.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ql-user")
public class UserController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
//    @RequestMapping("/")
//    public String homeInterface(){
//        return "user/login";
//    }

    @GetMapping("/account")
    public String account(Model theModel, HttpSession session){
        //The Attribute for the form
        theModel.addAttribute("newUser",new User());
        List<Role> roles = roleService.findAll();
        theModel.addAttribute("roleOption", roles);
        //The Attribute for list of members and pagination
        List<User> listMember = userService.findAll();
        theModel.addAttribute("list", listMember);
        theModel.addAttribute("size", listMember.size());
        //Show swal
        Boolean msg = (Boolean) session.getAttribute("msg");
        if(msg!=null){
            theModel.addAttribute("msg", msg);
            session.removeAttribute("msg");
        }
        return "admin/account";
    }
    @PostMapping("/save")
    public String submitForm(@ModelAttribute("newUser") User user, HttpSession session){
        String formattedDateTime = Date.date();
        user.setStatus(1);
        user.setCreated(formattedDateTime);
        userService.saveUser(user);
        //show swal
        session.setAttribute("msg", true);
        return "redirect:/ql-user/account";
    }
    @PostMapping("/delete")
    public String deleteMember(@RequestParam("idUserDel") int id){
        User user = userService.findById(id);
        user.setStatus(2);
        userService.saveUser(user);
        return "redirect:/ql-user/account";
    }
    @PostMapping("/update")
    public String update(@RequestParam("idUpdate") int id,
                         @RequestParam("fullName") String fullName,
                         @RequestParam("email") String email,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("address") String address,
                         @RequestParam("userName") String userName,
                         @RequestParam("role") Role role
                         ){
        User user = userService.findById(id);
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setAddress(address);
        user.setUserName(userName);
        user.setRole(role);
        userService.saveUser(user);
        return "redirect:/ql-user/account";
    }
    @PostMapping("/send-mail")
    public String send(@RequestParam("idForSend") int id, @RequestParam("note") String note){
        User user = userService.findById(id);
        user.setNote(note);
        userService.saveUser(user);
        return "redirect:/ql-user/account";
    }

    @PostMapping("/lock")
    public String lock(@RequestParam("idUserLock") int id){
        User user = userService.findById(id);
        user.setStatus(0);
        userService.saveUser(user);
        return "redirect:/ql-user/account";
    }
    @PostMapping("/unLock")
    public String unLock(@RequestParam("idUserUnlock") int id){
        User user = userService.findById(id);
        user.setStatus(1);
        userService.saveUser(user);
        return "redirect:/ql-user/account";
    }
}
