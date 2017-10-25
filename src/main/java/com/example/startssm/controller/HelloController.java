package com.example.startssm.controller;

import com.example.startssm.GirlPropertice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //    @Value("${cupSize}")
//    private String cupSize;
    @Autowired
    private GirlPropertice girlPropertice;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "Hello Spring Boot" + girlPropertice.getCupSize();
    }

    @RequestMapping(value = "/pathvari/{id}")
    public String loadPathVari(@PathVariable("id") Integer id) {
        return "id" + id;
    }

    @RequestMapping(value = "/{id}/pathvaritwo")
    public String loadPathVariTwo(@PathVariable("id") Integer id) {
        return "id" + id;
    }

    @RequestMapping(value = "/requestparams")
    public String loadRequestParam(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        return "id" + id;
    }
    @GetMapping(value = "/get")
    public String getLoad(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        return "id" + id;
    }
    @PostMapping(value = "/post")
    public String postLoad(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        return "id" + id;
    }


}
