package com.example.isaacwebproject.battle.controller;

import com.example.isaacwebproject.battle.service.BattleService;
import com.example.isaacwebproject.battle.vo.BattleVO;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
        String memId = (String)session.getAttribute("userInfo");
        battleService.addRoom(memId);
    }
}
