package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mybatisplus.entity.Picture;
import com.example.mybatisplus.mapper.PictureMapper;
import org.springframework.stereotype.Service;

@Service
public class PictureService extends ServiceImpl<PictureMapper, Picture> {

}