package com.example.isaacwebproject.battle.vo;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class BattleVO {
    private int roomId;
    private String mem1Id;
    private String mem2Id;
    private int mem1Item;
    private int mem2Item;
}
