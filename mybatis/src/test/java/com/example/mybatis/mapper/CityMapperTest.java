package com.example.mybatis.mapper;

import com.example.mybatis.model.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityMapperTest {
    @Autowired
    private CityMapper userMapper;
    @Test
    public void getAll() throws Exception {
        List<City> all = userMapper.getAll();
        System.out.println(all.size());
    }

    @Test
    public void findById(){
        City one = userMapper.findOneById(5);
        System.out.println(one.toString());
    }
    @Test
    public void findOneByProvince(){
        List<City> list = userMapper.findOneByProvince("湖南");
        System.out.println(list.toString());
    }
    @Test
    public void inserOne(){
        Integer id = userMapper.insertOne(new City("中国", "云南", "迪庆"));
        System.out.println(id);
    }
    @Test
    public void updateOne(){
        userMapper.update(new City(24,"中国","云南","大理"));
//        System.out.println(list.toString());
    }
    @Test
    public void deleteOne(){
        userMapper.delete(26);
//        System.out.println(list.toString());
    }

}