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

    @PutMapping
    public Result register(@RequestBody User user) {
        user.setBalance(new BigDecimal(0));
        return userService.register(user);
    }

    @GetMapping
    public Result getUsers() {
        return userService.getUsers();
    }
}
