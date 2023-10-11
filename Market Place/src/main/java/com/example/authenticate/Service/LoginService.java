package com.example.authenticate.Service;

import com.example.authenticate.Entity.LoginEntity;
import com.example.authenticate.model.Login;
import com.example.authenticate.repository.LoginRepository;
import com.example.authenticate.repository.SignupRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;


    public Map<String, String> save(Login l){
        Map<String, String> response = new HashMap<>();
        LoginEntity le=new LoginEntity();

        BeanUtils.copyProperties(l, le);

        Optional<LoginEntity> ole=loginRepository.findByEmailAndPass(le.getEmail(),le.getPass());
        System.out.println(le.getEmail());
        loginRepository.save(le);
        if(ole.isPresent()) {
            BeanUtils.copyProperties(ole,l);
            response.put("status","ok");
            response.put("user",l.getEmail());
        }
        else{
            response.put("status","error");
            response.put("message","User does not exists");
        }
        return response;
    }

    public Map<String, String> validate(Login l){
        String email=l.getEmail();
        String pass=l.getPass();
        Map<String, String> response = new HashMap<>();
        if(pass==null || pass.length()<6){
            response.put("status","error");
            response.put("message","Password should have more than 6 size");
        }
        else response.put("status","ok");
        return response;
    }
}
