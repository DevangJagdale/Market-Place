package com.example.authenticate.Entity;

import jakarta.persistence.Entity;
import  jakarta.persistence.Id;
import  jakarta.persistence.Table;
@Entity
@Table(name = "user_data")
public class LoginEntity {
    @Id
    private String email;
    private String pass;

    private Boolean isLoggedIn= false;

    // Constructors

    public LoginEntity() {
        // Default constructor
    }

    public LoginEntity(String email, String pass, Boolean isLoggedIn) {
        this.email = email;
        this.pass = pass;
        this.isLoggedIn=isLoggedIn;
    }

    // Getter and setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getisLoggedIn() {
        return isLoggedIn;
    }

    public void setisLoggedIn(Boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }
}
