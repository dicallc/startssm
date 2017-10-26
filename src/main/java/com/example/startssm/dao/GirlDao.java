package com.example.startssm.dao;

import com.example.startssm.domain.Girl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GirlDao extends JpaRepository<Girl, Integer> {
    public List<Girl> findByAge(Integer age);
}
