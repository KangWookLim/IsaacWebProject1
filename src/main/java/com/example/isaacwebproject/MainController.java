package com.example.isaacwebproject;

import com.example.isaacwebproject.items.service.ItemsService;
import com.example.isaacwebproject.items.vo.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final ItemsService itemsService;
    @GetMapping("/")
    public ModelAndView mainPage() {
        ModelAndView view = new ModelAndView();
        List<Items> itemList = this.itemsService.getAllItems();
        view.setViewName("views/home");
        view.addObject("itemList", itemList);
        return view;
    }

}

