package com.example.isaacwebproject.battle.vo;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class BattleRoom {
    @Id
    private int room_num;

    @Column(length = 50)
    private String mem1_Id;

    @Column(length = 50)
    private String mem2_Id;

    @ColumnDefault("0")
    private int MEM1_USE_ITEM_ID;

    @ColumnDefault("0")
    private int MEM2_USE_ITEM_ID;

    @Column(length = 50)
    private String Winner_Id;
}
