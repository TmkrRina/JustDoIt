package com.example.justdoit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @RequestMapping({"/welcome"})
    public String firstPage(){
        return "Welcome user";
    }
}
