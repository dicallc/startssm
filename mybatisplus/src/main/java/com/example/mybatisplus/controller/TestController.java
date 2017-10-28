package com.example.mybatisplus.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.mybatisplus.entity.BeautifulPictures;
import com.example.mybatisplus.service.BeautifulPicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    BeautifulPicturesService beautifulPicturesService;

    @RequestMapping("/test1")
    public String view(Model model, Page<BeautifulPictures> page) {
        Page<BeautifulPictures> pageList = beautifulPicturesService.selectPage(page);
        model.addAttribute("user", JSON.toJSONString(pageList.getRecords()));
        return "index";
    }
}
