package com.example.demo.Domain.Model;

import org.springframework.stereotype.Component;

import java.util.List;

public class Surfer {
    String emailAddress;
    List<String> subscribedLocations;
    int minWaveHeight;
    int maxWaveHeight;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<String> getSubscribedLocations() {
        return subscribedLocations;
    }

    public void addLocation(String location) {
        if(!this.subscribedLocations.contains(location))
        {
            this.subscribedLocations.add(location);
        }
    }
    public int getMinWaveHeight() {
        return minWaveHeight;
    }

    public void setMinWaveHeight(int minWaveHeight) {
        this.minWaveHeight = minWaveHeight;
    }

    public int getMaxWaveHeight() {
        return maxWaveHeight;
    }

    public void setMaxWaveHeight(int maxWaveHeight) {
        this.maxWaveHeight = maxWaveHeight;
    }

    @Override
    public String toString() {
        return "Surfer{" +
                "emailAddress='" + emailAddress + '\'' +
                ", subscribedLocations=" + subscribedLocations +
                '}';
    }
}
