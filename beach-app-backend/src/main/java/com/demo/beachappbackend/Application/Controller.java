package com.example.demo.Application;

import com.example.demo.Application.Exceptions.SurferStoreException;
import com.example.demo.Domain.Model.Surfer;
import com.example.demo.Domain.service.InMemorySurferStore;
import com.example.demo.Domain.service.SurfCheckerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class Controller {

    @Autowired
    public Controller(InMemorySurferStore surferStore, SurfCheckerService surfCheckerService){
        this.surferStore = surferStore;
        this.surfCheckerService = surfCheckerService;
    }

    @Operation(summary = "Enter user information to subscribe")
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
                    description = "Unable to store email address"
            )
    })
    @PostMapping(value = "subscribe", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity subscribe(@RequestBody Surfer user) throws SurferStoreException {
        System.out.println(user.getEmailAddress());
        if (user.getSubscribedLocations().isEmpty()){
            String responseBody = "Please select a beach";
            log.error(responseBody);
            return new ResponseEntity(responseBody,HttpStatus.NOT_ACCEPTABLE);
        }

        if (surfCheckerService.sendConfirmationEmail(user)){
            surferStore.addSurfer(user);
            String responseBody = "Successfully subscribed " + surferStore.getSurfer(user.getEmailAddress()).getEmailAddress();
            return new ResponseEntity(responseBody,HttpStatus.ACCEPTED);
        }
        String responseBody = "Failed to subscribed \"" + user.getEmailAddress() + "\". Invalid email address.";
        log.error(responseBody);
        return new ResponseEntity(responseBody,HttpStatus.NOT_ACCEPTABLE);
    }

    @Operation(summary = "Retrieve user information based on email address")
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
                    description = "Unable to get user"
            )
    })

    @GetMapping(value = "getUser")
    public String getUser(@RequestParam String email) throws SurferStoreException {
        return surferStore.getSurfer(email).toString();
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(SurferStoreException.class)
    public String handleSurfStoreException(SurferStoreException exception){
        return exception.getMessage();
    }

    private InMemorySurferStore surferStore;
    private SurfCheckerService surfCheckerService;

}
