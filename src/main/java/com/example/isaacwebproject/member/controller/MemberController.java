package com.example.isaacwebproject.member.controller;

import com.example.isaacwebproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mem")
public class MemberController {

    private final MemberService memberService;

        @GetMapping("/join")
    public ModelAndView join() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/join");
        return view;
    }

    @GetMapping("/chk")
    @ResponseBody
    public int memCheck(@RequestParam(name="ID") String ID) throws Exception {
        return memberService.checkUser(ID);
    }
    @GetMapping("/nickchk")
    @ResponseBody
    public int memNickCheck(@RequestParam(name="NICKNAME") String NICKNAME) throws Exception {
        return memberService.checkNickname(NICKNAME);
    }

    @PostMapping("/join")
    public ModelAndView insertMember(@RequestParam(name="ID") String ID,
                                     @RequestParam(name="PW") String PW,
                                     @RequestParam(name="NICKNAME") String NICKNAME) throws Exception {
        ModelAndView view = new ModelAndView();
        try{
            memberService.insertMember(ID,PW,NICKNAME);
            view.addObject("resultMsg", "가입을 축하드립니다.");
        }catch (Exception e) {
            view.addObject("resultMsg", e.getMessage());
            throw new Exception("가입이 실패하였습니다.");
        }
        view.setViewName("views/member/join_result");
        return view;
    }
}
