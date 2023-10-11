package com.example.authenticate.model;

import java.util.HashMap;
import java.util.Map;

public class Signup{
    private String name;
    private String email;
    private String pass;
    private Boolean isLoggedIn=false;

    //toString

    public String toString(Signup s){
        return s.name+" "+s.email+" "+s.pass;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
