package com.example.isaacwebproject.battle.repo;

import com.example.isaacwebproject.battle.vo.BattleRoom;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BattleJAPRepo extends JpaRepository<BattleRoom,Integer> {
}
