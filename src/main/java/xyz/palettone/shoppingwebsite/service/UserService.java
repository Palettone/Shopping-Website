package xyz.palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.User;

public interface UserService extends IService<User> {

    Result login(User user, HttpServletResponse response);
    Result register(User user);

    Result getUsers();
}
