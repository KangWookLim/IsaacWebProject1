package com.example.isaacwebproject.websocket.chat.Repo;

import com.example.isaacwebproject.websocket.chat.vo.ChatVo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ChatRepo {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<ChatVo> rowMapper = (rs, rowNum) ->
            new ChatVo(
              rs.getString("mem_id"),
              rs.getString("create_date"),
              rs.getString("message"),
              rs.getInt("id")
            );

    public List<ChatVo> findAllChat(){
        String sql = "select * from CHAT_LOG ORDER BY id";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addChatLog(String mem_id, String message){
        String sql = "insert into chat_log (mem_id, message, id) values (:mem_id, :message, chat_log_id.nextval)";
        Map<String,Object> params = Map.of("mem_id", mem_id, "message", message);
        jdbcTemplate.update(sql,params);
    }
}
