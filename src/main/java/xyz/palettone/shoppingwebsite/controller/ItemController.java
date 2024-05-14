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
        if (tag.getId() != 0) {
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
    public Result deleteTag(@RequestBody Tag tag){
        return itemService.deleteTag(tag.getTag());
    }

    @PutMapping()
    public Result modifyItem(@RequestBody Item item){
        return itemService.modifyItem(item);
    }

    @PutMapping("/tag")
    public Result modifyTag(@RequestBody Tag tag){
        return itemService.modifyTag(tag);
    }
}
