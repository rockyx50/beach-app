package com.demo.beachappbackend.Domain.Model;

public class Beach {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState_code() {
        return state_code;
    }

    public void setState_code(String state_code) {
        this.state_code = state_code;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getBeach_name() {
        return beach_name;
    }

    public void setBeach_name(String beach_name) {
        this.beach_name = beach_name;
    }

    public int getTier_rank() {
        return tier_rank;
    }

    public void setTier_rank(int tier_rank) {
        this.tier_rank = tier_rank;
    }

    public int getBeachlength_km() {
        return beachlength_km;
    }

    public void setBeachlength_km(int beachlength_km) {
        this.beachlength_km = beachlength_km;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    int id;
    String state_code;
    String county;
    String beach_name;
    int tier_rank;
    int beachlength_km;
    int rating;
}
