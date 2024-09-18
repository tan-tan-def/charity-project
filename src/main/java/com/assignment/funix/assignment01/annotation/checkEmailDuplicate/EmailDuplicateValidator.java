package com.assignment.funix.assignment01.annotation.checkEmailDuplicate;

import com.assignment.funix.assignment01.entity.User;
import com.assignment.funix.assignment01.service.user.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;


public class EmailDuplicateValidator implements ConstraintValidator<EmailNotDuplicate, String> {
    private UserService userService;

    @Autowired
    public EmailDuplicateValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(EmailNotDuplicate constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User userCheck = userService.findByEmail(value);
        return userCheck==null;
    }
}
