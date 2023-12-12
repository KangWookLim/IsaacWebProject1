package com.example.isaacwebproject.inven.controller;

import com.example.isaacwebproject.inven.service.InvenService;
import com.example.isaacwebproject.inven.vo.InvenVO;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import com.example.isaacwebproject.error.exception.DoNotLoginException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class InvenController {
    private final InvenService service;
    private final MemberMapper memberMapper;

    @RequestMapping("/inven")
    public ModelAndView inven(HttpServletRequest request){
        List<InvenVO> inven = new ArrayList<>();
        ModelAndView view = new ModelAndView();
        HttpSession session = request.getSession();
        String memId = (String)(session.getAttribute("userInfo"));
        if(memId == null) {
            throw new DoNotLoginException("로그인이 필요합니다.");
        }else {
            inven = service.findElementsByMemid(memId);
            view.addObject("inven", inven);
            view.setViewName("views/inven");
            return view;
        }
    }


//    @RequestMapping("/inven")
//    public List<InvenVO> getInvenData(){
//        List<InvenVO> inven = new ArrayList<>();
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        try {
//            inven = service.findElementsByMemid("1234");
//        }catch (Exception e) {
//            resultMap.put("resultCOde",500);
//            e.printStackTrace();
//        }
//
//        return inven;
//    }

}
