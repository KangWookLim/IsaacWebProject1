package com.example.isaacwebproject.items.controller;

import com.example.isaacwebproject.error.exception.DoNotLoginException;
import com.example.isaacwebproject.inven.service.InvenService;
import com.example.isaacwebproject.items.service.ItemsService;
import com.example.isaacwebproject.items.vo.Items;
import com.example.isaacwebproject.member.service.MemberSecurityService;
import com.example.isaacwebproject.member.service.MemberService;
import com.example.isaacwebproject.member.vo.Member;
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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final ItemsService itemsService;
    private final MemberService memberService;
    private final MemberSecurityService memberSecurityService;
    private final InvenService invenService;
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
    public ResponseEntity<String> orderItems(@RequestParam("ItemID") int ItemID, @RequestParam("Amount") int Amount, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = (String)(session.getAttribute("userInfo"));
        if(memId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }else {
            Member orderedMember = memberSecurityService.loadUserByUsername(memId);
            Items orderedItem = this.itemsService.getItem(ItemID);
            if (orderedMember.getCOIN() < orderedItem.getPrice()*Amount) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("소지 골드가 모자랍니다.");
            } else {
                memberService.updateCoinById(memId, orderedMember.getCOIN() - orderedItem.getPrice());
                if(invenService.checkItem(memId, orderedItem.getId())){
                    int invenAmount = invenService.findElementByMemIDandItemId(memId, orderedItem.getId()).getAmount();
                    invenService.UpdateItem(memId, orderedItem.getId(), invenAmount + Amount);
                }else {
                    invenService.InsertItem(memId, orderedItem.getId(), Amount);
                }
                return ResponseEntity.ok("구입성공");
            }
        }
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
