package com.example.isaacwebproject.battle.controller;

import com.example.isaacwebproject.battle.service.BattleService;
import com.example.isaacwebproject.battle.vo.BattleRoom;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/ws")
public class BattleController {
    private final BattleService battleService;
    private List<BattleRoom> rooms = Collections.synchronizedList(new ArrayList<>());

    @RequestMapping("/makerooms")
    @ResponseBody
    public synchronized void makeRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        BattleRoom room = new BattleRoom();
        room.setRoom_num(rooms.size() + 1);
        room.setMem1_Id(memId);
        rooms.add(room);
        System.out.println(rooms);
    }

    @RequestMapping("/deleterooms")
    @ResponseBody
    public synchronized void deleteRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        for (BattleRoom room : rooms) {
            if (room.getMem1_Id().equals(memId)) {
                rooms.remove(room);
                break;
            }
        }
    }

    @RequestMapping("/getroominfo")
    @ResponseBody
    public List<BattleRoom> getRoomInfos() {
        return rooms;
    }

    @RequestMapping("/checkroominfo")
    @ResponseBody
    public List<BattleRoom> checkRoomInfos(HttpServletRequest request) throws InterruptedException {
        String memId = (String) request.getSession().getAttribute("userInfo");
        long starttime = System.currentTimeMillis();
        while (true) {
            boolean isChanged = false;
            int time = (int) (System.currentTimeMillis() - starttime) / 1000;
            for (BattleRoom room : rooms) {
                if (room.getMem2_Id() != null) {
                    isChanged = true;
                    break;
                }
            }
            if (isChanged) {
                break;
            }
            if (time == 10) {
                break;
            }
            Thread.sleep(500);
        }

        return rooms;
    }

    @RequestMapping("/checkroom")
    @ResponseBody
    public List<BattleRoom> connectRoom() throws InterruptedException {
        int oldSessionLen = rooms.size();
        long starttime = System.currentTimeMillis();
        while (true) {
            int curruntSessionLen = rooms.size();
            int time = (int) (System.currentTimeMillis() - starttime) / 1000;
            if (time == 10) {
                break;
            }
            if (oldSessionLen != curruntSessionLen) {
                break;
            }
            Thread.sleep(500);
        }
        return rooms;
    }

    @RequestMapping("/getrooms")
    @ResponseBody
    public List<BattleRoom> getRoom() {
        return rooms;
    }

    @GetMapping("/intoroom")
    @ResponseBody
    public void intoroom(@RequestParam int room_num, HttpServletRequest request) {
        String mem_id = (String) request.getSession().getAttribute("userInfo");
        rooms.get(room_num-1).setMem2_Id(mem_id);
    }

    @RequestMapping("/ready")
    @ResponseBody
    public BattleRoom ready(@RequestParam String item_id, HttpServletRequest request) {
        
    }
}
