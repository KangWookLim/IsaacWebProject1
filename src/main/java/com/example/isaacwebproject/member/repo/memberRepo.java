package com.example.isaacwebproject.member.repo;

import com.example.isaacwebproject.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@RequiredArgsConstructor
public class memberRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<Member> rowMapper = (rs, rowNum) ->
            new Member(
                    rs.getInt("memid"),
                    rs.getString("id"),
                    rs.getString("pw"),
                    rs.getString("nickname"),
                    rs.getInt("coin"),
                    rs.getInt("exp"),
                    rs.getTimestamp("createdate").toLocalDateTime(),
                    rs.getTimestamp("updatedate").toLocalDateTime()
            );

    public Member findById(String id) {
        String sql = "select * FROM member WHERE id = :id";
        Map<String,Object> params = Map.of("id", id);
        return jdbcTemplate.queryForObject(sql,params,rowMapper);
    }
}
