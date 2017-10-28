package com.example.mybatis.mapper;

import com.example.mybatis.model.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CityMapper {
    @Select("SELECT * FROM city")
    @Results({
            @Result(property = "country",  column = "country"),
            @Result(property = "province", column = "name"),
            @Result(property = "city", column = "state"),
    })
    List<City> getAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Select("SELECT * FROM city WHERE id=#{id}")
    @Results({
            @Result(property = "country",  column = "country"),
            @Result(property = "province", column = "name"),
            @Result(property = "city", column = "state"),
    })
    City findOneById(Integer id);

    /**
     * 根据省份进行查询
     * @param province
     * @return
     */
    @Select("SELECT * FROM city WHERE name=#{province}")
    @Results({
            @Result(property = "country",  column = "country"),
            @Result(property = "province", column = "name"),
            @Result(property = "city", column = "state"),
    })
    List<City> findOneByProvince(String province);

    @Insert("INSERT INTO city (country,name,state)VALUE(#{country},#{province},#{city})")
    @Results({
            @Result(property = "id",  column = "id"),
    })
    Integer insertOne(City iCity);

    @Update("UPDATE city SET country=#{country},name=#{province},state=#{city} WHERE id=#{id}")
    void update(City city);

    @Delete("DELETE FROM city WHERE id=#{id}")
    void delete(Integer id);
}
