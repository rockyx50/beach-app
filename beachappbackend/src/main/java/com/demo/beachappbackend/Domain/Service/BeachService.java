package com.demo.beachappbackend.Domain.Service;

import com.demo.beachappbackend.Domain.Model.Beach;
import com.demo.beachappbackend.Domain.Model.BeachHistory;
import com.demo.beachappbackend.Infrastructure.Repository.JdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeachService {

    public List<Beach> getBeaches(String beachName){
        return jdbcRepository.getBeaches(beachName);
    }

    public List<BeachHistory> getBeachHistory(Integer beachId){
        return jdbcRepository.getBeachHistory(beachId);
    }

    public void submitBeachRating(BeachHistory beach){
        jdbcRepository.submitBeachRating(beach);
    }

    public Beach getBeachById(Integer id){
        return jdbcRepository.getBeachById(id);
    }

    public void calculateBeachRating(int beachId)
    {
        List<BeachHistory> beaches = jdbcRepository.getBeachHistory(beachId);
        float average = 0f;
        for(BeachHistory beachHistory: beaches){
            average += beachHistory.getRating();
            System.out.println(average);
        }
        average = average/beaches.size();
        jdbcRepository.updateBeachRating(beachId,average);
    }

    @Autowired
    JdbcRepository jdbcRepository;
}
