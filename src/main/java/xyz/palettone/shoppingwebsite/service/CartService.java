package xyz.palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Cart;

public interface CartService extends IService<Cart> {
    Result addCart(Long userId, Long itemId);

    Result getCart(Long userId);

    Result deleteCart(Long userId, Long itemId);

    Result getViewReport();
}
