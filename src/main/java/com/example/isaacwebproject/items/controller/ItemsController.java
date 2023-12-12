package com.example.isaacwebproject.items.controller;

import com.example.isaacwebproject.error.exception.DoNotLoginException;
import com.example.isaacwebproject.items.service.ItemsService;
import com.example.isaacwebproject.items.vo.Items;
import com.example.isaacwebproject.member.service.MemberSecurityService;
import com.example.isaacwebproject.member.service.MemberService;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final ItemsService itemsService;
    private final MemberService memberService;
    private final MemberSecurityService memberSecurityService;
    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public ModelAndView itemsList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Items> itemList = this.itemsService.getAllItems();
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("views/shop");
        return modelAndView;
    }

    @PostMapping("/shop/order")
    @ResponseBody
    public ResponseEntity<String> orderItems(@RequestParam("totalprice") int totalprice, HttpServletRequest request) {
        int coin;
        HttpSession session = request.getSession();
        String memId = (String)(session.getAttribute("userInfo"));
        if(memId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }else {
            coin = memberSecurityService.loadUserByUsername(memId).getCOIN();
            if(coin < totalprice) {
                //TODO: 거래실패 로직
            }else{
                memberService.updateCoinById(memId, coin-totalprice);
                return ResponseEntity.ok("success");
            }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
    }

    @RequestMapping(value = "/shop/search")
    public ModelAndView searchList(@RequestParam("keyword") String keyword) {
        ModelAndView modelAndView = new ModelAndView();
        List<Items> itemList = this.itemsService.searchItem(keyword);
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("views/shop");
        return modelAndView;
    }
}
