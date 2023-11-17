package com.example.beststore.Models;

public class NavCategoryModel {
    String img_url;
    String name;
    String  discount;
    String description;

    public NavCategoryModel(){

    }
    public NavCategoryModel(String img_url, String name, String discount, String description) {
        this.img_url = img_url;
        this.name = name;
        this.discount = discount;
        this.description = description;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
