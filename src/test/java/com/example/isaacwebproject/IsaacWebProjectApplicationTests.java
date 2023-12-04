package com.example.isaacwebproject;

import com.example.isaacwebproject.items.Repository.ItemsRepository;
import com.example.isaacwebproject.items.vo.Items;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class IsaacWebProjectApplicationTests {
    @Autowired
    private ItemsRepository itemsRepository;
    @Test
    void contextLoads() {
        Optional<Items> collections = this.itemsRepository.findById(1);
        if(collections.isPresent()) {
            Items c = collections.get();
            System.out.println(c);
        }
    }

}
