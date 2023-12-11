package com.example.isaacwebproject.items.vo;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Items {
    private Integer id;
    private String img_url;
    private Integer price;
    private String name;
    private String rarity;
    private String effect;
}
