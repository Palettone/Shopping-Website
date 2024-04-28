package xyz.palettone.shoppingwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Tag;
import xyz.palettone.shoppingwebsite.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping()
    public Result getAll(){
        return itemService.getAllItem();
    }

    @GetMapping("/tags")
    public Result getAllTags(){
        return  itemService.getAllTags();
    }
    @GetMapping("/{name}")
    public Result getItem(@PathVariable String name){
        return itemService.getItem(name);
    }

    @PostMapping()
    public Result insertItem(@RequestBody Item item){
        if (item.getId() != null) {
            return itemService.modifyItem(item);
        } else {
            return itemService.insertItem(item);
        }
    }

    @PostMapping("/tag")
    public Result insertTag(@RequestBody Tag tag){
        if (tag.getName() != null) {
            return itemService.modifyTag(tag);
        } else {
            return itemService.insertTag(tag);
        }
    }

    @DeleteMapping()
    public Result deleteItem(@RequestBody Item item){
        return itemService.deleteItem(item.getId());
    }

    @DeleteMapping("/tag")
    public Result deleteItem(@RequestBody Tag tag){
        return itemService.deleteTag(tag.getName());
    }

    @PutMapping()
    public Result modifyItem(@RequestBody Item item){
        return itemService.modifyItem(item);
    }

    @PutMapping()
    public Result modifyTag(@RequestBody Tag tag){
        return itemService.modifyTag(tag);
    }
}
