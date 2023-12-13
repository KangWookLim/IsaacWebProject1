package com.example.isaacwebproject.inven.repo;

import com.example.isaacwebproject.inven.vo.InvenVO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
                    rs.getString("img_url")
            );
    private final RowMapper<InvenVO> rowMapper2 = (rs, rowNum) ->
            new InvenVO(
                    rs.getString("mem_id"),
                    rs.getInt("items_id"),
                    rs.getInt("amount")
            );

    public List<InvenVO> getAllInvenDatas(){
        String query = "SELECT * FROM inventory";
        return jdbcTemplate.query(query, rowMapper);
    }
    public List<InvenVO> findElementsByMemid(String memId){
        String query =
                "SELECT inv.*, i.IMG_URL img_url FROM inventory inv LEFT OUTER JOIN items i ON inv.ITEMS_ID = i.ID WHERE mem_id = :memId ORDER BY ITEMS_ID";
        Map<String, Object> param = Map.of("memId", memId);
        return jdbcTemplate.query(query, param, rowMapper);
    }

    public InvenVO findElementByMemIDandItemId(String memId, int itemId){
        String query =
                "SELECT inv.*, i.IMG_URL img_url FROM inventory inv LEFT OUTER JOIN items i ON inv.ITEMS_ID = i.ID WHERE mem_id = :memId AND items_id = :itemsId";
        Map<String, Object> param = Map.of("memId", memId, "itemsId", itemId);
        return jdbcTemplate.queryForObject(query, param, rowMapper);
    }

    public int InsertItem(String memId, int itemsId, int amount){
        String query =
                "INSERT INTO inventory (mem_id, items_id, amount) VALUES (:memId, :itemsId, :amount)";
        Map<String, Object> param = Map.of("memId", memId, "itemsId", itemsId, "amount", amount);
        return jdbcTemplate.update(query, param);
    }

    public void DeleteItem(String memId, int itemsId){
        String query =
                "DELETE FROM inventory WHERE mem_id = :memId AND items_id = :itemsId";
        Map<String, Object> param = Map.of("memId", memId, "itemsId", itemsId);
        jdbcTemplate.update(query, param);
    }
    public void UpdateItem(String memId, int itemsId, int amount) {
        String query =
                "UPDATE inventory SET amount = :amount WHERE mem_id = :memId AND items_id = :itemsId";
        Map<String, Object> param = Map.of("memId", memId, "itemsId", itemsId, "amount", amount);
        jdbcTemplate.update(query, param);
    }
    public boolean CheckItem(String memId, int itemsId){
        List<InvenVO> invenList = new ArrayList<InvenVO>();
        String query =
                "SELECT * FROM inventory WHERE mem_id = :memId AND items_id = :itemsId";
        Map<String, Object> param = Map.of("memId", memId, "itemsId", itemsId);
        return !jdbcTemplate.query(query, param, rowMapper2).isEmpty();
    }
}


