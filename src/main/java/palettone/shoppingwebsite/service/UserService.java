package palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletResponse;
import palettone.shoppingwebsite.dto.Result;
import palettone.shoppingwebsite.entity.User;

public interface UserService extends IService<User> {

    Result login(User user, HttpServletResponse response);
    Result register(User user);

    Result getUsers();
}
