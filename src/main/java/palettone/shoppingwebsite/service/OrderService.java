package palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import palettone.shoppingwebsite.dto.Result;
import palettone.shoppingwebsite.entity.Order;

public interface OrderService extends IService<Order> {
    Result newOrder(Long userId);

    Result finishOrder(Long id);

    Result getReport();

    Result getAllOrder();
}
