package xyz.palettone.shoppingwebsite.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.User;
import xyz.palettone.shoppingwebsite.service.UserService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result login(@RequestBody User user, HttpServletResponse response) {
        return userService.login(user, response);
    }
    @PostMapping("role")
    public Result modifyUser(@RequestBody User user){
            return userService.modifyUser(user);
    }

    @PutMapping
    public Result register(@RequestBody User user) {
        user.setBalance(new BigDecimal(0));
        return userService.register(user);
    }
    @GetMapping("tag")
    public Result getUserById(@CookieValue("user") Long userId){
        return userService.getUserById(userId);
    }
    @GetMapping
    public Result getUsers() {
        return userService.getUsers();

    }
    @GetMapping("/address")
    public Result getAddress(@RequestParam String username){
        return userService.getAddress(username);
    }


    @GetMapping("/wealth")
    public Result getWealth(@RequestParam String username){
        return userService.getWealth(username);
    }
    @GetMapping("/favourite_tag")
    public Result getFavouriteTag(@RequestParam String username){
        return userService.getFavouriteTag(username);
    }
    @DeleteMapping
    public Result resetPsd(@RequestBody String username){
        return userService.resetPsd(username);
    }
}
