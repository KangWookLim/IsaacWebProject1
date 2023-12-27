package com.example.isaacwebproject.battle.vo;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class BattleRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int room_Id;

    @Column(length = 50)
    private String mem1_Id;

    @Column(length = 50)
    private String mem2_Id;

    private int MEM1_USE_ITEM_ID;

    private int MEM2_USE_ITEM_ID;
}
