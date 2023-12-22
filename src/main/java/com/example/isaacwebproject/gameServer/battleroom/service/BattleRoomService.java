package com.example.isaacwebproject.gameServer.battleroom.service;

import com.example.isaacwebproject.gameServer.battleroom.repo.BattleRoomRepo;
import com.example.isaacwebproject.gameServer.battleroom.vo.BattleRoomVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BattleRoomService {
    private final BattleRoomRepo repo;
    public BattleRoomVo findById(int id) {
        Optional<BattleRoomVo> optionalBattleRoomVo = repo.findById(id);
        return optionalBattleRoomVo.orElse(null);
    }
    public Integer findNumByMemId(String memId){
        if(repo.findNumByMemId(memId)==null){
            return null;
        }
        else return repo.findNumByMemId(memId);
    }
}
