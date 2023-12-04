package com.example.isaacwebproject.items.Repository;

import com.example.isaacwebproject.items.vo.Items;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public class ItemsRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Items> rowMapper = (rs, rowNum) ->
            new Items(
                    rs.getInt("id"),
                    rs.getString("img_url"),
                    rs.getInt("price"),
                    rs.getString("name")
            );
    public ItemsRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<Items> findById(int id) {
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
        String sql = "SELECT * FROM items";
        return jdbcTemplate.query(sql, rowMapper);
    }

}
