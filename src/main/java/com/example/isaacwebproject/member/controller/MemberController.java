package com.example.isaacwebproject.member.controller;

import com.example.isaacwebproject.member.service.MemberService;
import com.example.isaacwebproject.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/mem/join")
    public ModelAndView join() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/join");
        return view;
    }

    @GetMapping("/mem/chk")
    @ResponseBody
    public Map<String, Object> memCheck(@RequestParam(name="ID") String ID) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            int result = memberService.checkUser(ID);

            if (result == 0) {
                resultMap.put("resultCode", 200);
            }else {
                throw new Exception("중복된 아이디가 존재합니다.");
            }
        }catch (Exception e) {
            resultMap.put("resultCode", 500);
            e.printStackTrace();
        }

        return resultMap;
    }

    @PostMapping("/mem/join")
    public ModelAndView insertMember(@ModelAttribute Member.Request request) {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/join_result");
        try{
            System.out.println("controller " + request.getID() + request.getPW() + request.getNickname());

            int result = memberService.insertMember(request);

            if(result > 0) {
                view.addObject("resultMsg", "가입을 축하드립니다.");
            } else {
                throw new Exception("가입이 실패하였습니다.");
            }

        }catch (Exception e) {
            view.addObject("resultMsg", e.getMessage());
            e.printStackTrace();
        }

        return view;
    }


    }
