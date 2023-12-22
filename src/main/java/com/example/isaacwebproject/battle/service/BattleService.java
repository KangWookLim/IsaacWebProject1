package com.example.isaacwebproject.battle.service;

import com.example.isaacwebproject.battle.repo.BattleRepo;
import com.example.isaacwebproject.battle.vo.BattleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BattleService {
    private final BattleRepo battleRepo;

    public List<BattleVO> findAllRoom(){

        return battleRepo.findAllRoom();
    }
    public void addRoom(String mem_id){
        battleRepo.addBattleroom(mem_id);
    }

    public void deleteRoom(String mem_id){
        battleRepo.deleteBattleroom(mem_id);
    }
}
