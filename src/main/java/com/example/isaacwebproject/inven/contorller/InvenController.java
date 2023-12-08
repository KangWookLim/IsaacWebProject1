package com.example.isaacwebproject.inven.contorller;

import com.example.isaacwebproject.inven.service.InvenService;
import com.example.isaacwebproject.inven.vo.InvenVO;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import com.example.isaacwebproject.member.vo.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequiredArgsConstructor
public class InvenController {

    private final InvenService service;
    private final MemberMapper memberMapper;

    @RequestMapping("/inven")
    public ModelAndView inven() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/inven");
        return view;
    }

    @RequestMapping(value ="/inven", method = { RequestMethod.GET})
    public List<InvenVO> getInvenData(@RequestParam(name="ID", required = true)String ID){
        List<InvenVO> inven = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            inven = service.getInvenData(ID);
        }catch (Exception e) {
            resultMap.put("resultCOde",500);
            e.printStackTrace();
        }

        return inven;
    }
}
