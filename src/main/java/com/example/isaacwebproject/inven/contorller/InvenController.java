package com.example.isaacwebproject.inven.contorller;

import com.example.isaacwebproject.inven.service.InvenService;
import com.example.isaacwebproject.inven.vo.InvenVO;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class InvenController {

    private final InvenService service;
    private final MemberMapper memberMapper;

    @RequestMapping("/inven")
    public ModelAndView inven() throws Exception {
        List<InvenVO> inven = new ArrayList<>();
        ModelAndView view = new ModelAndView();
        inven = service.findElementsByMemid("1234");
        view.setViewName("views/inven");
        return view;
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
