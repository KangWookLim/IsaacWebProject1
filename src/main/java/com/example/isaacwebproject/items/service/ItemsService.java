package com.example.isaacwebproject.items.service;

import com.example.isaacwebproject.error.exception.DataNotFoundException;
import com.example.isaacwebproject.items.Repository.ItemsRepository;
import com.example.isaacwebproject.items.vo.Items;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;
    public Items getItem(int id) {
        Optional<Items> opItems = this.itemsRepository.findById(id);
        if (opItems.isPresent()) {
            return opItems.get();
        }else {
                throw new DataNotFoundException("items not found");
        }
    }

    public List<Items> getAllItems() {
        return this.itemsRepository.findAll();
    }
}
