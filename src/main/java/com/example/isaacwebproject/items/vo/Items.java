package com.example.isaacwebproject.items.vo;

import lombok.*;

@Data
public class Items {
    private Integer id;
    private String img_url;
    private Integer price;
    private String name;

    public Items(int id, String imgUrl, int price, String name) {
        this.id = id;
        this.img_url = imgUrl;
        this.price = price;
        this.name = name;
    }
}