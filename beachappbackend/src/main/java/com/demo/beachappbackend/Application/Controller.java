package com.demo.beachappbackend.Application;

import com.demo.beachappbackend.Application.Exceptions.BeachException;
import com.demo.beachappbackend.Domain.Model.Beach;
import com.demo.beachappbackend.Domain.Model.BeachHistory;
import com.demo.beachappbackend.Domain.Service.BeachService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/beachappbackend")

public class Controller {

    @Operation(summary = "Retrieve list of beach information based on beach name")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response Message"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Illegal argument"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unable to find beach"
            )
    })

    @GetMapping(value = "getBeachInfo")
    public List<Beach> getBeachInfo(@RequestParam String beachName) throws BeachException {
        return beachService.getBeaches(beachName);
    }

    @Operation(summary = "Retrieve random list of beach information")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Response Message"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Illegal argument"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Unable to find beach"
            )
    })

    @GetMapping(value = "getRandomBeachInfo")
    public List<Beach> getBeachInfo(@RequestParam Integer numberOfBeaches) throws BeachException {
        List<Beach> beachList = new ArrayList<>();
        for(int i = 0; i < numberOfBeaches; i++){
            int randNum = (int) (Math.random() * 100 + 1);
            beachList.add(beachService.getBeachById(randNum));
        }
        return beachList;
    }

    @GetMapping(value = "getBeachHistory")
    public List<BeachHistory> getBeachHistory(@RequestParam Integer beachId) throws BeachException {
        return beachService.getBeachHistory(beachId);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(BeachException.class)
    public String handleBeachException(BeachException exception){
        return exception.getMessage();
    }

    @Autowired
    BeachService beachService;


}