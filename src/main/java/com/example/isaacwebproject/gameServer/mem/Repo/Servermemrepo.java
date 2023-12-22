package com.example.isaacwebproject.gameServer.mem.Repo;

import com.example.isaacwebproject.gameServer.mem.Vo.memVo;
import lombok.RequiredArgsConstructor;
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
                    rs.getString("createdate"),
                    rs.getString("updatedate")
            ));
    public Optional<memVo> findById(String id) {
        String sql = "select * from member where id = :id";
        Map<String,Object> param = Map.of("id",id);
        memVo vo = jdbcTemplate.queryForObject(sql,param,rowMapper);
        return Optional.ofNullable(vo);
    }
}
