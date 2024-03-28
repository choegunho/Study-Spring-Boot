package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(String title, Integer price){
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }

    public Optional<Item> findItem(Integer id){
        Optional<Item> result = itemRepository.findById(id);
        return result;
    }

    public void updateItem(Integer id, String title, Integer price){
        Optional<Item> result = itemRepository.findById(id);
        if(!result.isPresent()){
            throw new IllegalArgumentException("Item with id " + id + " not found.");
        } else{
            Item item = result.get();
            item.setTitle(title);
            item.setPrice(price);
            itemRepository.save(item);
        }
    }
}
