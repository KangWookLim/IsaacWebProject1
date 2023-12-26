package com.example.isaacwebproject.gameServer.battleroom.repo;

import com.example.isaacwebproject.gameServer.battleroom.vo.BattleRoomVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BattleRoomRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<BattleRoomVo> rowMapper = ((rs, rowNum) ->
            new BattleRoomVo(
                    rs.getInt("room_id"),
                    rs.getString("mem1_id"),
                    rs.getString("mem2_id"),
                    rs.getInt("mem1_use_item_id"),
                    rs.getInt("mem2_use_item_id")
            ));
    public Optional<BattleRoomVo> findById(int roomId) {
        String sql = "select * from battleroom where room_id = :room_id";
        Map<String, Object> params = Map.of("room_id",roomId);
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,params,rowMapper));
        }catch (Exception e){
            return Optional.empty();
        }
    }

    public int findNumByMemId(String memId) {
        String sql = "select ROOM_ID from battleroom where mem1_id = :memid OR mem2_id = :memid";
        Map<String, Object> params = Map.of("memid",memId);
        int res;
        try{
            res = jdbcTemplate.queryForObject(sql,params,Integer.class);
            return res;
        }catch (Exception e) {
            return 0;
        }
    }
}
