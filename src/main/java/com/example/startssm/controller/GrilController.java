package com.example.startssm.controller;

import com.example.startssm.dao.GirlDao;
import com.example.startssm.domain.Girl;
import com.example.startssm.service.GirlService;
import com.example.startssm.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

//    @GetMapping(value = "/addgirl")
//    public Girl addGirl(@RequestParam("cupSize") String cupSize, @RequestParam("age")
//            Integer age) {
//        Girl girl = new Girl(cupSize, age);
//        return girlDao.save(girl);
//    }
    /*优化传入参数,带验证*/
    @GetMapping(value = "/addgirl")
    public Object addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        Girl girls = new Girl(girl.getCupSize(), girl.getAge());
        return girlDao.save(girls);
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
    @GetMapping(value = "girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
        girlService.getAge(id);
    }

}
