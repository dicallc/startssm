package com.example.startssm.service;

import com.example.startssm.dao.GirlDao;
import com.example.startssm.domain.Girl;
import com.example.startssm.enums.ResultEnum;
import com.example.startssm.exception.GirlException;
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
    public void getAge(Integer id) throws Exception{
        Girl girl = girlDao.findOne(id);
        Integer age = girl.getAge();
        if (age < 10) {
            //返回"你还在上小学吧" code=100
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }

        //如果>16岁,加钱
        //...
    }
}
