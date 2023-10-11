package com.example.authenticate.Service;

import com.example.authenticate.Entity.SignupEntity;
import com.example.authenticate.model.Signup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.authenticate.repository.SignupRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SignupService {
    @Autowired
     private SignupRepository signupRepository;

    public Map<String,String> save(Signup s){
        Map<String, String> response = new HashMap<>();
        SignupEntity se=new SignupEntity();
        BeanUtils.copyProperties(s, se);

        Optional<SignupEntity> res=signupRepository.findById(se.getEmail());

        if(res.isPresent()){
            response.put("status","error");
            response.put("message","User already exists");

            return response;
        }

        response.put("status","ok");
        response.put("user",s.getEmail());
        signupRepository.save(se);

        return response;
    }

    public Map<String, String> validate(Signup s){
        String name=s.getName();
        String email=s.getEmail();
        String pass=s.getPass();
        Map<String, String> response = new HashMap<>();
        if(pass==null || pass.length()<6){
            response.put("status","error");
            response.put("message","Password should have more than 6 size");
        }
        else response.put("status","ok");
        return response;
    }
}
