package xyz.palettone.shoppingwebsite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.palettone.shoppingwebsite.mapper.OrderItemMapper;
import xyz.palettone.shoppingwebsite.service.OrderService;

@SpringBootTest
public class OrderItemTest {
    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    OrderService orderService;

    @Test
    public void test(){
        orderService.newOrder(1L);
        System.out.println(orderItemMapper.selectCountByItemId(5L));
    }
}
