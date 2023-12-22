package com.example.isaacwebproject.gameServer.battleroom.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class BattleRoomVo {
    private int room_id;
    private String mem1_id;
    private String mem2_id;
    private int mem1_use_item_id;
    private int mem2_use_item_id;
}
