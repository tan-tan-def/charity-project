package com.assignment.funix.assignment01.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "note")
    private String note;
    @Column(name = "created")
    private String created;
    @Column(name="status")
    private int status;
    /*
    status = 0: Khóa
    status = 1: Hoạt động
    status = 2: Xóa
     */
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(mappedBy = "user",
               cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<UserDonation> userDonations;
    public User() {
    }

    public User(int id,String address, String email, String password, String phoneNumber, String userName, String fullName, String note, String created, int status, Role role) {
        this.id = id;
        this.address = address;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.fullName = fullName;
        this.note = note;
        this.created = created;
        this.status = status;
        this.role = role;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<UserDonation> getUserDonations() {
        return userDonations;
    }

    public void setUserDonations(List<UserDonation> userDonations) {
        this.userDonations = userDonations;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", role='" + role + '\'' +
                ", note='" + note + '\'' +
                ", created='" + created + '\'' +
                ", status=" + status +
                '}';
    }
    public void add(UserDonation tempUserDonation){
        if(userDonations==null){
            userDonations=new ArrayList<>();
        }
        userDonations.add(tempUserDonation);
        tempUserDonation.setUser(this);
    }
}
