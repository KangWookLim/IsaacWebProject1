package com.example.isaacwebproject.gameServer.mem.Repo;

import com.example.isaacwebproject.gameServer.mem.Vo.memVo;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.SqlScriptException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class Servermemrepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<memVo> rowMapper = ((rs, rowNum) ->
            new memVo(
                    rs.getInt("memid"),
                    rs.getString("id"),
                    rs.getString("pw"),
                    rs.getString("nickname"),
                    rs.getInt("coin"),
                    rs.getInt("exp"),
                    rs.getTimestamp("createdate").toLocalDateTime(),
                    rs.getTimestamp("updatedate").toLocalDateTime()
            ));
    public Optional<memVo> findById(String id) {
        String sql = "select * from member where id = :id";
        Map<String,Object> param = Map.of("id",id);
        try{
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,param,rowMapper));
        }catch (Exception e){
            return Optional.empty();
        }
    }
    public void SingleincreaseCoinById(String id){
        String sql = "update member set COIN = COIN+200 where ID = :id";
        Map<String,Object> param = Map.of("id",id);
        try{
            jdbcTemplate.update(sql,param);
        }catch (SqlScriptException e){
            e.printStackTrace();
        }
    }

    public void multiIncreaseCoinById(String id){
        String sql = "update member set COIN = COIN+500 where ID = :id";
        Map<String,Object> param = Map.of("id",id);
        try{
            jdbcTemplate.update(sql,param);
        }catch (SqlScriptException e){
            e.printStackTrace();
        }
    }
}
