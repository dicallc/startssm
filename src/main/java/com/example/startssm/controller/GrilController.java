package com.example.startssm.controller;

import com.example.startssm.dao.GirlDao;
import com.example.startssm.domain.Girl;
import com.example.startssm.service.GirlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GrilController {
    @Autowired
    private GirlDao girlDao;
    @Autowired
    private GirlService girlService;

    @GetMapping(value = "/loadgirl")
    public List<Girl> girlList() {
        return girlDao.findAll();
    }

    @GetMapping(value = "/addgirl")
    public Girl addGirl(@RequestParam("cupSize") String cupSize, @RequestParam("age")
            Integer age) {
        Girl girl = new Girl(cupSize, age);
        return girlDao.save(girl);
    }

    @GetMapping(value = "/insertgirl")
    public void addGirl() {
        girlService.insertTwo();
    }

    @GetMapping(value = "/loadgirl/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id) {
        return girlDao.findOne(id);
    }

    @GetMapping(value = "/updategirl/{id}")
    public Girl updategirl(@PathVariable("id") Integer id, @RequestParam("cupSize") String cupSize, @RequestParam("age") Integer age) {
        Girl girl = new Girl(cupSize, age);
        girl.setId(id);
        return girlDao.save(girl);
    }

    @GetMapping(value = "/deletegirl/{id}")
    public void updategirl(@PathVariable("id") Integer id) {
        girlDao.delete(id);
    }

    @GetMapping(value = "/findbygirl/{id}")
    public void girlFindByGirl(@PathVariable("id") Integer id) {
        girlDao.findByAge(id);
    }

}
