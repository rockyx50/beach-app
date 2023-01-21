package com.demo.beachappbackend.Domain.Service;

import com.demo.beachappbackend.Domain.Model.Beach;
import com.demo.beachappbackend.Infrastructure.Repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeachService {

    public List<Beach> getBeaches(String beachName){
        return jdbcRepository.getBeaches(beachName);
    }

    public Beach getBeachById(Integer id){
        return jdbcRepository.getBeachById(id);
    }

    @Autowired
    JdbcRepository jdbcRepository;
}
