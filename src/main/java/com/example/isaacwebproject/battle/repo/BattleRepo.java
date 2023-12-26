package com.example.isaacwebproject.battle.repo;

import com.example.isaacwebproject.battle.vo.BattleVO;
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
    private final RowMapper<BattleVO> rowMapper = (rs, rowNum) ->
            new BattleVO(
                    rs.getInt("ROOM_ID"),
                    rs.getString("MEM1_ID"),
                    rs.getString("MEM2_ID"),
                    rs.getInt("MEM1_USE_ITEM_ID"),
                    rs.getInt("MEM2_USE_ITEM_ID")
            );

    public List<BattleVO> findAllRoom(){
        String sql = "SELECT * FROM battleroom ORDER BY room_id";
        return jdbcTemplate.query(sql, rowMapper);
    }
    public void addBattleroom(String memId){
        String sql = "INSERT INTO battleroom (room_ID, mem1_id) VALUES (battleroom_up.nextval, :MEM1_ID)";
        Map<String, Object> params = Map.of("MEM1_ID",memId);
        jdbcTemplate.update(sql, params);
    }

    public void deleteBattleroom(String memId){
        String sql = "DELETE FROM battleroom WHERE MEM1_ID = :MEM1_ID";
        Map<String, Object> params = Map.of("MEM1_ID", memId);
        jdbcTemplate.update(sql,params);
    }
    public List<BattleVO> getRoomInfo(String memId){
        String sql = "SELECT * FROM battleroom Where MEM1_ID = :MEM1_ID";
        Map<String, Object> params = Map.of("MEM1_ID", memId);
        return jdbcTemplate.query(sql, params, rowMapper);
    }


}
