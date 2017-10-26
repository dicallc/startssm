package com.example.startssm.service;

import com.example.startssm.dao.GirlDao;
import com.example.startssm.domain.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GirlService {
    @Autowired
    private GirlDao girlDao;

    @Transactional
    public void insertTwo() {
        Girl girl = new Girl("F", 18);
        girlDao.save(girl);
        Girl girl2 = new Girl("F", 42);
        girlDao.save(girl2);

    }
}
