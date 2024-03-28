package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    String list(Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);

        return "list.html";
    }

    @GetMapping("/write")
    String write(){
        return "write.html";
    }

    @PostMapping("/addition")
    String addPost(String title, Integer price) {
        itemService.saveItem(title, price);
        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    String detail(Model model, @PathVariable Integer id){

        Optional<Item> result = itemService.findItem(id);
        if( result.isPresent() ){
            model.addAttribute("data", result.get());
            return "detail.html";
        } else{
                return "redirect:/list";
        }
    }

    @GetMapping("/update")
    String update(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);

        return "update.html";
    }

    @PostMapping("/update")
    String update(Integer id, String title, Integer price){
        itemService.updateItem(id, title, price);
        return "redirect:/list";
    }
}
