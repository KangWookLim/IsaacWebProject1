package com.example.isaacwebproject.items.Repository;

import com.example.isaacwebproject.items.vo.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ItemsRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Items> rowMapper = (rs, rowNum) ->
            new Items(
                    rs.getInt("id"),
                    rs.getString("img_url"),
                    rs.getInt("price"),
                    rs.getString("name"),
                    rs.getString("rarity"),
                    rs.getString("effect")
            );


    public Optional<Items> findById(int id){
        String sql = "SELECT * FROM items WHERE id = :id";
        try{
            Map<String, Object> param = Map.of("id", id);
            Items items = jdbcTemplate.queryForObject(sql, param, rowMapper);
            return Optional.of(items);
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    public List<Items> findAll() {
        String sql = "SELECT * FROM items ORDER BY id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Items> searchItems(String keyword) {
        String sql = "SELECT * FROM items WHERE name like :keyword";
        Map<String, Object> params = Map.of("keyword", "%" + keyword + "%");
        return jdbcTemplate.query(sql, params, rowMapper);
    }



}
