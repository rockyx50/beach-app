package com.demo.beachappbackend.Infrastructure.Repository;

import com.demo.beachappbackend.Domain.Model.Beach;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JdbcRepository {

    public List<Beach> getBeaches(String beachName){
        return jdbcTemplate.query(QUERY_FOR_LIST, new BeanPropertyRowMapper<Beach>(Beach.class), beachName);
    }

    public Beach getBeachById(Integer id){
        return jdbcTemplate.queryForObject(QUERY_FOR_BEACH_BY_ID, new BeanPropertyRowMapper<Beach>(Beach.class), id);
    }
    private static final String QUERY_FOR_LIST = "SELECT * FROM beaches where beaches.name = ? ";
    private static final String QUERY_FOR_BEACH_BY_ID = "SELECT * FROM beachlist where id = ? ";


    @Autowired
    JdbcTemplate jdbcTemplate;

}
