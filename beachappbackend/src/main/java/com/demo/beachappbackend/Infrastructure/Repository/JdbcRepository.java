package com.demo.beachappbackend.Infrastructure.Repository;

import com.demo.beachappbackend.Domain.Model.Beach;
import com.demo.beachappbackend.Domain.Model.BeachHistory;
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
    public List<BeachHistory> getBeachHistory(Integer beachId){
        return jdbcTemplate.query(QUERY_FOR_BEACH_HISTORY, new BeanPropertyRowMapper<>(BeachHistory.class), beachId);
    }

    public void submitBeachRating(BeachHistory beach){
        jdbcTemplate.update(SUBMIT_BEACH_RATING,beach.getId(),beach.getBeach_name(),beach.getRating(),beach.getUser());
    }

    public Beach getBeachById(Integer id){
        return jdbcTemplate.queryForObject(QUERY_FOR_BEACH_BY_ID, new BeanPropertyRowMapper<Beach>(Beach.class), id);
    }

    public void updateBeachRating(Integer id, float rating){
        jdbcTemplate.update(UPDATE_BEACH_RATING,rating,id);
        Beach temp = jdbcTemplate.queryForObject(QUERY_FOR_BEACH_BY_ID, new BeanPropertyRowMapper<Beach>(Beach.class), id);
        System.out.println(temp.toString());
    }
    private static final String QUERY_FOR_LIST = "SELECT * FROM beachlist where beach_name = ? ";
    private static final String QUERY_FOR_BEACH_BY_ID = "SELECT * FROM beachlist where id = ? ";
    private static final String QUERY_FOR_BEACH_HISTORY = "SELECT * FROM beach_history where beach_id = ? ";
    private static final String SUBMIT_BEACH_RATING = "INSERT INTO beach_history(beach_id,beach_name,rating,\"user\") VALUES (?,?,?,?) ";
    private static final String UPDATE_BEACH_RATING = "UPDATE beachlist SET rating = ? WHERE id = ?";


    @Autowired
    JdbcTemplate jdbcTemplate;

}
