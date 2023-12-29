package com.example.beststore.Models;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

public class AllProductModel implements  Serializable {
    String  name;
    String img_url;
    int price;
    public  AllProductModel(){

    }

    public AllProductModel(String name, String img_url, int price) {
        this.name = name;
        this.img_url = img_url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

