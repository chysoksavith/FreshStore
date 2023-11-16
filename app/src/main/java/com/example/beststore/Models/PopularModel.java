package com.example.beststore.Models;

public class PopularModel {
    String name;
    String description;
    String discound;
    String rating;
    String type;
    String img_url;

    public  PopularModel(){

    }
    public PopularModel(String name, String description, String discound, String rating, String type, String img_url) {
        this.name = name;
        this.description = description;
        this.discound = discound;
        this.rating = rating;
        this.type = type;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscound() {
        return discound;
    }

    public void setDiscound(String discound) {
        this.discound = discound;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
