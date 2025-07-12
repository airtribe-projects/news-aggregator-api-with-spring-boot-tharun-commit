package com.example.NewsAggregatorAPIwithSpringBoot.Entity;

public class PreferencesDto {

    private String category;
    private String country;

    public PreferencesDto() {
    }

    public PreferencesDto(String category, String country) {
        this.category = category;
        this.country = country;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}