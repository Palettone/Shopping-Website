package xyz.palettone.shoppingwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.palettone.shoppingwebsite.entity.OrderItem;
import xyz.palettone.shoppingwebsite.mapper.OrderItemMapper;
import xyz.palettone.shoppingwebsite.service.OrderItemService;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
