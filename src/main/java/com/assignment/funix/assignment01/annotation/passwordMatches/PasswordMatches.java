package com.assignment.funix.assignment01.annotation.passwordMatches;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {
    String message() default "Mật khẩu nhập lại xác nhận không khớp";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
