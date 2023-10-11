package com.example.authenticate.Controller;

import com.example.authenticate.Entity.UserEntity;
import com.example.authenticate.model.Signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.authenticate.Service.HomeService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @PostMapping("/home/{id}")
    public Map<String,String> getUserDetails(@RequestBody Signup email, @PathVariable String id){
        System.out.println("id: "+id);
        Map<String, String> response = new HashMap<>();
        Optional<UserEntity> allow=homeService.getData(id);

        if(allow.isPresent() && allow.get().getisLoggedIn()){
            response.put("status","ok");
            response.put("name",allow.get().getName());
        }
        else{
            response.put("status","error");
        }

        return response;
    }

    @PutMapping("/logout")
    public void getUserLoggedOut(@RequestBody Signup formData){

        homeService.getUserLoggedout(formData.getEmail());
        return;
    }
}
