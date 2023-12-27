package com.example.isaacwebproject.battle.service;

import com.example.isaacwebproject.battle.repo.BattleJAPRepo;
import com.example.isaacwebproject.battle.repo.BattleRepo;
import com.example.isaacwebproject.battle.vo.BattleRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BattleService {
    private final BattleRepo battleRepo;
    private final BattleJAPRepo battleJAPRepo;

    public List<BattleRoom> findAllRoom(){
        return battleRepo.findAllRoom();
    }

    public void deleteRoom(String mem_id){
        battleRepo.deleteBattleroom(mem_id);
    }
    public List<BattleRoom> getRoomInfo(String mem_id){
        return battleRepo.getRoomInfo(mem_id);
    }
    public void updateBattleRoomMem2(String mem_id, int room_num){
        this.battleRepo.updateMem2idByRoomNum(mem_id, room_num);
    }

    public BattleRoom findByMem_Id(String memId) throws SQLException {
        return battleRepo.findByMem_Id(memId);
    }
    public void addBattleRoom(BattleRoom room){
        battleJAPRepo.save(room);
    }
}
