package com.example.isaacwebproject.items.vo;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String img_url;

    private Integer price;

    @Column(length = 100)
    private String name;

    @Column(length = 50)
    private String rarity;

    @Column(length = 500)
    private String effect;
}
