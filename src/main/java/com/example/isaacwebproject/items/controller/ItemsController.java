package com.example.isaacwebproject.items.controller;

import com.example.isaacwebproject.items.service.ItemsService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ItemsController {
    private final ItemsService itemsService;
//    @GetMapping("/")
//    public ModelAndView CollectionsList() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("views/home");
//        Collections collection = this.collectionsService.getCollection(1);
//        modelAndView.addObject("collection", collection);
//        return modelAndView;
//    }

}
