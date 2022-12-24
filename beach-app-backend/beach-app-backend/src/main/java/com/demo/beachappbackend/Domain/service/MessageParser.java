package com.example.demo.Domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MessageParser {

    private Map<String, String> messageMap;
    private Map<String,JsonNode> forecastMap = new HashMap<>();
    private String header;
    private String body;

    public void parseData() {
        try {
            messageMap = mapper.readValue(Paths.get(emailMessagePath).toFile(), Map.class);
            File folder = new File(folderPath);
            for (File fileEntry : folder.listFiles()){
                JsonNode forecast = mapper.readTree(fileEntry);
                String location = fileEntry.getName();
                int endIndex = location.indexOf("_forecast");
                location = location.substring(0,endIndex);
                location = location.replace("_", " ");
                forecastMap.put(location,forecast);
            }
        }
        catch (IOException e) {
            log.error("Error parsing file: {}", e.getMessage());
        }
    }

    public void resetNotificationEmailTemplate(){
        try {
            messageMap = mapper.readValue(Paths.get(emailMessagePath).toFile(), Map.class);
        } catch (IOException e) {
            log.error("Unable to reset notification email template");
        }
        setHeader(messageMap.get("subject"));
        setBody(messageMap.get("body"));
    }

    public void resetConfirmationEmailTemplate(){
        try {
            messageMap = mapper.readValue(Paths.get(confirmationEmailPath).toFile(), Map.class);
        } catch (IOException e) {
            log.error("Unable to reset confirmation email template");
        }
        setHeader(messageMap.get("subject"));
        setBody(messageMap.get("body"));
    }



    public Map<String, String> getMessageMap(){return messageMap;}

    public Map<String, JsonNode> getForecastMap(){return forecastMap;}

    public String getHeader(){return header;}

    public String getBody(){return body;}

    public void setHeader(String header) {this.header = header; }

    public void setBody(String body) {this.body = body; }

    public int getMinWaveHeight(int index, String location) {
        JsonNode beachData = forecastMap.get(location);
        JsonNode data = beachData.get(index);
        return data.get("swell").get("minBreakingHeight").asInt();
    }

    public int getMaxWaveHeight(int index, String location) {
        JsonNode beachData = forecastMap.get(location);
        JsonNode data = beachData.get(index);
        return data.get("swell").get("maxBreakingHeight").asInt();
    }

    public void updateNotificationEmail(int index, String location) throws JsonProcessingException {
        JsonNode beachData = forecastMap.get(location);
        JsonNode data = beachData.get(index);


        this.header = header.replace("MIN",data.get("swell").get("minBreakingHeight").toString() + " " + data.get("swell").get("unit").toString());
        this.header = header.replace("MAX",data.get("swell").get("maxBreakingHeight").toString() + " " + data.get("swell").get("unit").toString());
        this.header = header.replace("TIME", Instant.ofEpochSecond(data.get("timestamp").asLong()).toString());
        this.header = header.replace("LOCATION",location);

        this.body = body.replace("MIN",data.get("swell").get("minBreakingHeight").toString() + " " + data.get("swell").get("unit").toString());
        this.body = body.replace("MAX",data.get("swell").get("maxBreakingHeight").toString() + " " + data.get("swell").get("unit").toString());
        this.body = body.replace("WIND_DIRECTION",data.get("wind").get("direction").toString() + " " + data.get("wind").get("compassDirection").toString());
        this.body = body.replace("WIND_SPEED",data.get("wind").get("speed").toString() + " " + data.get("wind").get("unit").toString());
        this.body = body.replace("TIME",Instant.ofEpochSecond(data.get("timestamp").asLong()).toString());
        this.body = body.replace("LOCATION",location);
        this.body = body.replace("GUST",data.get("wind").get("gusts").toString() + " " + data.get("wind").get("unit").toString());
        this.body = body.replace("AIR_TEMP",data.get("condition").get("temperature").toString() + " " + data.get("condition").get("unit").toString());
        this.body = body.replace("FEELS_TEMP",data.get("wind").get("chill").toString() + " " + data.get("condition").get("unit").toString());
        this.body = body.replace("SWELL_URL",data.get("charts").get("swell").toString());
        this.body = body.replace("PERIOD_URL",data.get("charts").get("period").toString());
        this.body = body.replace("WIND_URL",data.get("charts").get("wind").toString());
    }

    public void updateConfirmationEmail(List<String> locations, int minWaveHeight, int maxWaveHeight){
        this.body = body.replace("LOCATIONS", String.join(", ", locations));
        this.body = body.replace("MIN_WAVE_HEIGHT", String.valueOf(minWaveHeight));
        this.body = body.replace("MAX_WAVE_HEIGHT", String.valueOf(maxWaveHeight));
    }

@Value("${email.forecast.folder}")
private String folderPath;

@Value("${email.notification.file}")
private String emailMessagePath;

@Value("${email.confirmation.file}")
private String confirmationEmailPath;

@Autowired
ObjectMapper mapper;
}
