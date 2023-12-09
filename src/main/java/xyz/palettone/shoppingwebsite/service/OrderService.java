package xyz.palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Order;

import java.util.List;

public interface OrderService extends IService<Order> {
    Result newOrder(Long userId);

    Result newOrder(Long userId, List<Long> itemId);

    Result finishOrder(Long id);

    Result getReport();

    Result getAllOrder();
}
