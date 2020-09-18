package com.example.justdoit.controller;

import com.example.justdoit.config.JwtTokenUtil;
import com.example.justdoit.model.UserDTO;
import com.example.justdoit.repo.UserRepo;
import com.example.justdoit.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SignUpController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;



    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO user){
        System.out.println(user.getPassword());


        if (passwordValidator(user.getPassword())) {
            System.out.println(user.getUsername());

            try {
                jwtUserDetailsService.save(user);
            }
            catch (Exception e){
                return ResponseEntity.badRequest().body(e);
            }

            return ResponseEntity.ok(jwtTokenUtil.generateToken(jwtUserDetailsService.loadUserByUsername(user.getUsername())));
        }
        else{

             return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Invalid password format");
        }

    }

    private boolean passwordValidator(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        Pattern p = Pattern.compile(regex);
        if (password == null) {;
            return false;

        }

        Matcher m = p.matcher(password);
        return m.matches();

    }




}
