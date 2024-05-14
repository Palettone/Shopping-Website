package xyz.palettone.shoppingwebsite.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.service.LogService;
import xyz.palettone.shoppingwebsite.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;


    @GetMapping()
    public Result newLog(HttpServletRequest request, @CookieValue("user") Long userId,@RequestParam String type){
        String ipAddress =logService.getIpAddr(request);
        return logService.newLog(ipAddress,userId,type);
    }
    @GetMapping("all")
    public Result getAllLog(){
        return logService.getAllLog();
    }
    }
//    @GetMapping("palettone/{Itemid}")
//    public Result newOrder(@CookieValue("user") Long userId, @PathVariable List<Long> Itemid){
//        return orderService.newOrder(userId,Itemid);
//    }
//
//    @GetMapping("/{id}")
//    public Result finishOrder(@PathVariable Long id){
//        return orderService.finishOrder(id);
//    }
//
//    @GetMapping("/all")
//    public Result getReport(){
//        return orderService.getReport();
//    }
//
//    @GetMapping("/allOrder")
//    public Result getAllOrder(){
//        return orderService.getAllOrder();
//    }

