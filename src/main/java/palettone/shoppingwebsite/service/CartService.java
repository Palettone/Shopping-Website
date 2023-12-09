package palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import palettone.shoppingwebsite.dto.Result;
import palettone.shoppingwebsite.entity.Cart;

public interface CartService extends IService<Cart> {
    Result addCart(Long userId, Long itemId);

    Result getCart(Long userId);

    Result deleteCart(Long userId, Long itemId);

    Result getViewReport();
}
