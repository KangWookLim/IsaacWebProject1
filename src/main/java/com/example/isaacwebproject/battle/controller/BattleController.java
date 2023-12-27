package com.example.isaacwebproject.battle.controller;

import com.example.isaacwebproject.battle.service.BattleService;
import com.example.isaacwebproject.battle.vo.BattleRoom;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BattleController {
    private final BattleService battleService;

    //    @GetMapping("/chat")
//    public synchronized ModelAndView AddRoom(HttpServletRequest request){
//
//
//        return null;
//    }
    @RequestMapping("/ws/makerooms")
    @ResponseBody
    public void makeRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        battleService.addRoom(memId);
    }

    @RequestMapping("/ws/deleterooms")
    @ResponseBody
    public void deleteRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        battleService.deleteRoom(memId);
    }

    @RequestMapping("/ws/getroominfo")
    @ResponseBody
    public List<BattleRoom> getRoomInfos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        return battleService.getRoomInfo(memId);
    }

    @RequestMapping("/ws/checkroominfo")
    @ResponseBody
    public List<BattleRoom> checkRoomInfos(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String) session.getAttribute("userInfo");
        int oldSessionLen = battleService.getRoomInfo(memId).size();
        return battleService.getRoomInfo(memId);
    }
}
