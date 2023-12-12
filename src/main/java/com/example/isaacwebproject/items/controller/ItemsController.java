package com.example.isaacwebproject.items.controller;

import com.example.isaacwebproject.items.service.ItemsService;
import com.example.isaacwebproject.items.vo.Items;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final ItemsService itemsService;
    @RequestMapping("/shop")
    public ModelAndView itemsList() {
        ModelAndView modelAndView = new ModelAndView();
        List<Items> itemList = this.itemsService.getAllItems();
        modelAndView.addObject("itemList", itemList);
        modelAndView.setViewName("views/shop");
        return modelAndView;
    }

//    @RequestMapping("/shop/search")
//    public ModelAndView searchList(String keyword) {
//        ModelAndView modelAndView = new ModelAndView();
//        List<Items> itemList = this.itemsService.searchItem(keyword);
//        modelAndView.addObject("itemList", itemList);
//        modelAndView.setViewName("views/shop");
//        return modelAndView;
//    }



}
