package com.example.isaacwebproject.member.controller;

import com.example.isaacwebproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

        @RequestMapping("/mem/join")
    public ModelAndView join() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/join");
        return view;
    }

    @GetMapping("/mem/chk")
    @ResponseBody
    public int memCheck(@RequestParam(name="ID") String ID) throws Exception {
        return memberService.checkUser(ID);
    }

    @PostMapping("/mem/join")
    public ModelAndView insertMember(@RequestParam(name="ID") String ID,
                                     @RequestParam(name="PW") String PW,
                                     @RequestParam(name="NICKNAME") String NICKNAME)  {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/join_result");
        try{
            Boolean result = memberService.insertMember(ID,PW,NICKNAME);
            if(result) {
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
