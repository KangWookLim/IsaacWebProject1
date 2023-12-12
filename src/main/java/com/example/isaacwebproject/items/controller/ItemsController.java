package com.example.isaacwebproject.items.controller;

import com.example.isaacwebproject.items.service.ItemsService;
import com.example.isaacwebproject.items.vo.Items;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
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
    public Integer orderItems(@RequestParam("totalprice") int totalprice) {
        System.out.println(totalprice);
        return 2000-totalprice;
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
