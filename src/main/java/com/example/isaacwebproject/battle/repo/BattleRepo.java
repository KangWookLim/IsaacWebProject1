package com.example.isaacwebproject.battle.repo;

import com.example.isaacwebproject.battle.vo.BattleRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BattleRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<BattleRoom> rowMapper = (rs, rowNum) ->
            new BattleRoom(
                    rs.getInt("ROOM_ID"),
                    rs.getString("MEM1_ID"),
                    rs.getString("MEM2_ID"),
                    rs.getInt("MEM1_USE_ITEM_ID"),
                    rs.getInt("MEM2_USE_ITEM_ID")
            );

    public List<BattleRoom> findAllRoom(){
        String sql = "SELECT * FROM battle_room ORDER BY room_id";
        return jdbcTemplate.query(sql, rowMapper);
    }
    public void addBattleroom(String memId){
        String sql = "INSERT INTO battle_room (room_ID, mem1_id) VALUES (battleroom_up.nextval, :MEM1_ID)";
        Map<String, Object> params = Map.of("MEM1_ID",memId);
        jdbcTemplate.update(sql, params);
    }

    public void deleteBattleroom(String memId){
        String sql = "DELETE FROM battle_room WHERE MEM1_ID = :MEM1_ID";
        Map<String, Object> params = Map.of("MEM1_ID", memId);
        jdbcTemplate.update(sql,params);
    }
    public List<BattleRoom> getRoomInfo(String memId){
        String sql = "SELECT * FROM battle_room Where MEM1_ID = :MEM1_ID";
        Map<String, Object> params = Map.of("MEM1_ID", memId);
        return jdbcTemplate.query(sql, params, rowMapper);
    }


}
