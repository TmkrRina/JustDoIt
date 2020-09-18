package com.example.justdoit.service;

import java.util.ArrayList;

import com.example.justdoit.model.UserDTO;
import com.example.justdoit.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.justdoit.model.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

@Autowired
UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userRepo.findByUsername(username);
        System.out.println("user in the service = ");
        System.out.print(user);
        if (user == null) {
            System.out.println("null");
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        System.out.println("here last");
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
    public void save(UserDTO user) throws Exception{
        UserModel newuser = new UserModel();
        newuser.setUsername(user.getUsername());
        newuser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(newuser);

    }


}