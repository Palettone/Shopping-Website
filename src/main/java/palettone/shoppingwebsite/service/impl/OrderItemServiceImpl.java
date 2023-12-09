package palettone.shoppingwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import palettone.shoppingwebsite.entity.OrderItem;
import palettone.shoppingwebsite.mapper.OrderItemMapper;
import palettone.shoppingwebsite.service.OrderItemService;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
