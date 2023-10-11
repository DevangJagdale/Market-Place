package com.example.authenticate.Service;

import com.example.authenticate.Entity.SignupEntity;
import com.example.authenticate.Entity.UserEntity;
import com.example.authenticate.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HomeService {

    @Autowired
    private HomeRepository homeRepository;
    public Optional<UserEntity> getData(String email){
        if(email==null || email.isEmpty())return Optional.empty();
        return homeRepository.findById(email);

    }

    public void getUserLoggedout(String email){
        UserEntity ue=new UserEntity();
        ue.setEmail(email);
        ue.setisLoggedIn(false);
        homeRepository.save(ue);
    }
}
