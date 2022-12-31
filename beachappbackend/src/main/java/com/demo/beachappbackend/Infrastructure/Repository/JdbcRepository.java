package com.demo.beachappbackend.Infrastructure.Repository;

import com.demo.beachappbackend.Domain.Model.Beach;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class JdbcRepository {

    public List<Beach> getBeaches(String beachName){
        return jdbcTemplate.queryForList(QUERY_FOR_LIST, Beach.class, beachName);
    }

    private static final String QUERY_FOR_LIST = "SELECT * FROM beaches where beaches.name = ? ";

    @Autowired
    JdbcTemplate jdbcTemplate;

}
