package com.assignment.funix.assignment01.controller;

import com.assignment.funix.assignment01.dto.UserDTO;
import com.assignment.funix.assignment01.entity.Role;
import com.assignment.funix.assignment01.entity.User;
import com.assignment.funix.assignment01.service.role.RoleService;
import com.assignment.funix.assignment01.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public RegisterController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/show-page")
    public String showPage(Model theModel){
        theModel.addAttribute("userDTO", new UserDTO());
        return "public/register";
    }
    @PostMapping("/submit-register")
    public String register(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult result, Model theModel){
        if(!result.hasErrors()){
            theModel.addAttribute("message", "");
            Role role = roleService.findById(userDTO.getRole());
            User user = userService.register(userDTO);
            user.setRole(role);
            userService.saveUser(user);
            return "redirect:/login-page";
        }else {
//            theModel.addAttribute("userDTO", userDTO);
//            result.getAllErrors().forEach(error ->
//                    System.out.println("Error field: " + ((FieldError)error).getField() + " - " + error.getDefaultMessage())
//            );
//            System.out.println("Lỗi");
            return "public/register";
        }
    }
}
