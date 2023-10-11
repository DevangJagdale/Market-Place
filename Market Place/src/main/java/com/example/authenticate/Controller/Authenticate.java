package com.example.authenticate.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.authenticate.Service.SignupService;
import com.example.authenticate.Service.LoginService;
import com.example.authenticate.model.Login;
import com.example.authenticate.model.Signup;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Authenticate {
    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<?> SignUp(@RequestBody Signup signupForm){
        Signup s=new Signup();

        s.setName(signupForm.getName());
        s.setEmail(signupForm.getEmail());
        s.setPass(signupForm.getPass());
        s.setisLoggedIn(false);

        Map<String,String> isValid=signupService.validate(s);
        if(isValid.get("status").equals("error"))return ResponseEntity.status(HttpStatus.OK).body(isValid);

        Map<String,String> res=signupService.save(s);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestBody Login signupForm){
        Login l=new Login();

        l.setEmail(signupForm.getEmail());
        l.setPass(signupForm.getPass());
        l.setisLoggedIn(true);

        Map<String,String> user=loginService.save(l);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


}
