package com.assignment.funix.assignment01.annotation.passwordMatches;

import com.assignment.funix.assignment01.dto.UserDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserDTO> {
    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context){
        if(userDTO.getPassword()!=null&&userDTO.getConfirmPassword()!=null){
            return userDTO.getPassword().equals(userDTO.getConfirmPassword());
        }
        return false;
    }
}
