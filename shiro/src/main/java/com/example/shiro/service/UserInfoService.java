package com.example.shiro.service;

import com.example.shiro.domain.UserInfo;

public interface UserInfoService {
    /**通过username查找用户信息;*/
    public UserInfo findByUsername(String username);
}
