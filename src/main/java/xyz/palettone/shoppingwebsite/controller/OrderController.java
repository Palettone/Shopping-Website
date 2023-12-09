package xyz.palettone.shoppingwebsite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public Result newOrder(@CookieValue("user") Long userId){
        return orderService.newOrder(userId);
    }

    @GetMapping("palettone/{Itemid}")
    public Result newOrder(@CookieValue("user") Long userId, @PathVariable List<Long> Itemid){
        return orderService.newOrder(userId,Itemid);
    }

    @GetMapping("/{id}")
    public Result finishOrder(@PathVariable Long id){
        return orderService.finishOrder(id);
    }

    @GetMapping("/all")
    public Result getReport(){
        return orderService.getReport();
    }

    @GetMapping("/allOrder")
    public Result getAllOrder(){
        return orderService.getAllOrder();
    }
}
