package com.example.isaacwebproject.websocket.chat.Repo;

import com.example.isaacwebproject.websocket.chat.vo.Chat_Log;
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
    private final RowMapper<Chat_Log> rowMapper = (rs, rowNum) ->
            new Chat_Log(
                    rs.getInt("chat_log_id"),
                    rs.getString("mem_id"),
                    rs.getTimestamp("created_date").toLocalDateTime(),
                    rs.getString("message")

            );

    public List<Chat_Log> findAllChat(){
        String sql = "select * from CHAT_LOG ORDER BY created_date desc";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void addChatLog(String mem_id, String message){
        String sql = "insert into chat_log (mem_id, message) values (:mem_id, :message)";
        Map<String,Object> params = Map.of("mem_id", mem_id, "message", message);
        jdbcTemplate.update(sql,params);
    }
}
