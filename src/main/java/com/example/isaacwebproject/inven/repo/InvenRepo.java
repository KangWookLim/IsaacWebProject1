package com.example.isaacwebproject.inven.repo;

import com.example.isaacwebproject.inven.vo.InvenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class InvenRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    private final RowMapper<InvenVO> rowMapper = (rs, rowNum) ->
            new InvenVO(
                    rs.getString("mem_id"),
                    rs.getInt("items_id"),
                    rs.getInt("amount"),
                    rs.getInt("used"),
                    rs.getString("img_url")
            );
    public List<InvenVO> findElementsByMemid(String memId){
        String query =
                "SELECT inv.*, i.IMG_URL img_url FROM inventory inv LEFT OUTER JOIN items i ON inv.ITEMS_ID = i.ID WHERE mem_id = :memId";
        Map<String, Object> param = Map.of("memId", memId);
        return jdbcTemplate.query(query, param, rowMapper);
    }
}
