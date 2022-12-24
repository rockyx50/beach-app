package com.example.demo.Domain.service;

import com.example.demo.Application.Exceptions.SurferStoreException;
import com.example.demo.Domain.Model.Surfer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.util.List;

@Slf4j
@Service
public class SurfCheckerService implements Runnable{

    @Override
    public void run() {
        System.out.println(getForecast());
    }

    public ResponseEntity<String> getForecast(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
//        String url = "https://postman-echo.com/get";

        for (String locationId : messageParser.getForecastMap().keySet()){
            try{
                messageParser.resetNotificationEmailTemplate();
                messageParser.updateNotificationEmail(counter,locationId);
                if (counter + 1 >= messageParser.getForecastMap().get(locationId).size()) {
                    counter = -1;
                }
                counter++;
            }
            catch(JsonProcessingException e){
                log.error("Unable to update email message.");
            }
            sendEmails(locationId, counter);
            
        }


//        System.out.println(messageParser.getHeader());
//        System.out.println(messageParser.getBody());

        return null; //restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }
    
    private void sendEmails(String locationId, int counter){
        for (String email : surferStore.getSurferMap().keySet()) {
            try {
                Surfer user = surferStore.getSurfer(email);
                List<String> subscribedLocations = user.getSubscribedLocations();
                if (subscribedLocations.contains(locationId)){
                    int minWaveHeightForecast = messageParser.getMinWaveHeight(counter, locationId);
                    int maxWaveHeightForecast = messageParser.getMaxWaveHeight(counter, locationId);
                    if (minWaveHeightForecast >= user.getMinWaveHeight() && maxWaveHeightForecast <= user.getMaxWaveHeight()){
                        emailService.sendEmail(List.of(email), messageParser.getHeader(), messageParser.getBody());
                    }
                }
            } catch (SurferStoreException e) {
                log.error("Unable to find surfer {} in memory store",email);
            }
            catch (MessagingException e) {
                log.error("Unable to send notification email. {}",e.getMessage());
            }
        }
    }

    public boolean sendConfirmationEmail(Surfer user){
        messageParser.resetConfirmationEmailTemplate();
        messageParser.updateConfirmationEmail(user.getSubscribedLocations(), user.getMinWaveHeight(), user.getMaxWaveHeight());
        try{
            emailService.sendEmail(List.of(user.getEmailAddress()), messageParser.getHeader(), messageParser.getBody());
            return true;
        } catch (MessagingException e) {
            log.error("Unable to send confirmation email. {}",e.getMessage());
            return false;
        }
    }



@Autowired
RestTemplate restTemplate;

@Autowired
EmailService emailService;

@Autowired
InMemorySurferStore surferStore;

@Autowired
MessageParser messageParser;

private static int counter = 0;

}
