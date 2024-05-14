package xyz.palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.User;

public interface UserService extends IService<User> {

    Result modifyUser(User user);

    Result login(User user, HttpServletResponse response);
    Result register(User user);
    Result getUserById(Long userId);
    Result getAddress(String username);
    Result getWealth(String username);
    Result getFavouriteTag(String username);

    Result getUsers();
    Result resetPsd(String username);
}
