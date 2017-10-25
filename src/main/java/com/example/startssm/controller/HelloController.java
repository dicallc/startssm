package com.example.startssm.controller;

import com.example.startssm.GirlPropertice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

//    @Value("${cupSize}")
//    private String cupSize;
    @Autowired
    private GirlPropertice girlPropertice;

    @RequestMapping(value = "/hello")
    public String hello(){
        return "Hello Spring Boot"+girlPropertice.getCupSize();
    }
}
