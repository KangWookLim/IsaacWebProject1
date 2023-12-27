package com.example.isaacwebproject.battle.repo;

import com.example.isaacwebproject.battle.vo.BattleRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BattleRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<BattleRoom> rowMapper = (rs, rowNum) ->
            new BattleRoom(
                    rs.getInt("ROOM_NUM"),
                    rs.getString("MEM1_ID"),
                    rs.getString("MEM2_ID"),
                    rs.getInt("MEM1_USE_ITEM_ID"),
                    rs.getInt("MEM2_USE_ITEM_ID"),
                    rs.getString("Winner_ID")
            );

    public List<BattleRoom> findAllRoom(){
        String sql = "SELECT * FROM battle_room ORDER BY room_num";
        return jdbcTemplate.query(sql, rowMapper);
    }
    public void addBattleroom(String memId, int room_num){
        String sql = "INSERT INTO battle_room (mem1_id, ROOM_NUM) VALUES (:memId, :room_num)";
        Map<String, Object> params = Map.of("memId",memId,"room_num",room_num);
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

    public void updateMem2idByRoomNum(String memId, int roomnum){
        String sql = "UPDATE battle_room SET MEM2_ID = :memId WHERE ROOM_NUM = :roomnum";
        Map<String, Object> params = Map.of("memId", memId,"roomnum",roomnum);
        jdbcTemplate.update(sql,params);
    }

    public BattleRoom findByMem_Id(String memId) throws SQLException {
        String sql = "SELECT * FROM battle_room WHERE MEM1_ID = :memId OR MEM2_ID = :memId";
        Map<String, Object> params = Map.of("memId", memId);
        jdbcTemplate.getJdbcTemplate().getDataSource().getConnection().commit();
        try{
            return  jdbcTemplate.queryForObject(sql,params,rowMapper);
        }catch (Exception e){
            return null;
        }

    }

}
