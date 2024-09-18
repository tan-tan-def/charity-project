package com.assignment.funix.assignment01.dto;

import com.assignment.funix.assignment01.annotation.checkEmailDuplicate.EmailNotDuplicate;
import com.assignment.funix.assignment01.annotation.passwordMatches.PasswordMatches;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@PasswordMatches
public class UserDTO {

    @NotNull(message = "Full name is required")
    @Size(min = 1, max = 255, message = "Username must be between 1 and 255 characters")
    private String fullName;

    @EmailNotDuplicate(message = "Email already have used")
    @NotNull(message = "Email is required")
    private String email;

    @Size(min = 1, max = 255, message = "Username must be between 1 and 255 characters")
    private String username;

    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private String confirmPassword;

    @NotNull(message = "Confirm Password is required")
    private Integer role;
    public UserDTO(String fullName, String email, String username, String password, String confirmPassword, Integer role) {
        this.fullName = fullName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
    }

    public UserDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    // Optional: Override toString() method for better debugging
    @Override
    public String toString() {
        return "UserDTO{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}

