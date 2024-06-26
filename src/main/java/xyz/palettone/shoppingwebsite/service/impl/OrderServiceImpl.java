package xyz.palettone.shoppingwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import xyz.palettone.shoppingwebsite.VO.BuyReport;
import xyz.palettone.shoppingwebsite.VO.ItemReport;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.*;
import xyz.palettone.shoppingwebsite.mapper.*;
import xyz.palettone.shoppingwebsite.service.MailService;
import xyz.palettone.shoppingwebsite.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final CartMapper cartMapper;

    private final ItemMapper itemMapper;

    private final OrderMapper orderMapper;

    private final UserMapper userMapper;

    private final OrderItemMapper orderItemMapper;

    private final MailService mailService;

    @Value("${pserver}")
    String pserver;

    @Value("${server.port}")
    String port;

    @Override
    @Transactional
    public Result newOrder(Long userId) {
        List<Cart> carts = cartMapper.selectByUserId(userId);
        if (carts.isEmpty()) {
            return Result.fail("购物车为空");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (Cart cart : carts) {
            Item item = itemMapper.selectById(cart.getItemId());
            if (item.getCount() < cart.getCount()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.fail("库存不足");
            }
            item.setCount(item.getCount() - cart.getCount());
            item.setSold(cart.getCount());
            itemMapper.updateById(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(item.getId());
            orderItem.setCount(cart.getCount());
            orderItems.add(orderItem);
            total = total.add(item.getPrice().multiply(new BigDecimal(cart.getCount())));
            cartMapper.deleteById(cart.getId());
        }
        User user = userMapper.selectById(userId);
        if (user.getBalance().compareTo(total) < 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.fail("余额不足");
        }
        user.setBalance(user.getBalance().subtract(total));
        userMapper.updateById(user);
        Order order = new Order();
        order.setUserId(userId);
        order.setAmount(total);
        orderMapper.insert(order);
        for(OrderItem orderItem:orderItems){
            orderItem.setOrderId(order.getId());
            orderItemMapper.insert(orderItem);
        }
//        mailService.sendMail(user.getEmail(),
//                "完成订单：" + order.getId(),
//                "点击链接完成订单：http://localhost:18080/order/" + order.getId());
        return Result.ok("下单成功");
    }
    @Override
    @Transactional
    public Result newOrder(Long userId,List<Long> itemId) {
        //
        // String itemIdsStr = StringUtils.strip(itemId.toString(), "[", "]");
        String string = itemId.toString();
        string = string.substring(1, string.length() - 1);
        System.out.println(string);
        List<Cart> carts = cartMapper.queryUsersBatch(userId,string);
        if (carts.isEmpty()) {
            return Result.fail("购物车为空");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = new BigDecimal(0);
        for (Cart cart : carts) {
            Item item = itemMapper.selectById(cart.getItemId());
            if (item.getCount() < cart.getCount()) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.fail("库存不足");
            }
            item.setCount(item.getCount() - cart.getCount());
            itemMapper.updateById(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItemId(item.getId());
            orderItem.setCount(cart.getCount());
            orderItems.add(orderItem);
            total = total.add(item.getPrice().multiply(new BigDecimal(cart.getCount())));
            cartMapper.deleteById(cart.getId());
        }
        User user = userMapper.selectById(userId);
        if (user.getBalance().compareTo(total) < 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.fail("余额不足");
        }
        user.setBalance(user.getBalance().subtract(total));
        userMapper.updateById(user);
        Order order = new Order();
        order.setUserId(userId);
        order.setAmount(total);
        orderMapper.insert(order);
        for(OrderItem orderItem:orderItems){
            orderItem.setOrderId(order.getId());
            orderItemMapper.insert(orderItem);
        }
        mailService.sendMail(user.getEmail(),
                "完成订单：" + order.getId(),
                "点击链接完成订单：http://"+pserver+":"+port+"/order/" + order.getId());
        return Result.ok("下单成功");
    }

    @Override
    public Result finishOrder(Long id) {
        Order order = orderMapper.selectById(id);
        if (order == null) {
            return Result.fail("订单不存在");
        }
        if (order.isFinish()) {
            return Result.fail("订单已完成");
        }
        order.setFinish(true);
        orderMapper.updateById(order);
        return Result.ok("订单完成");
    }

    @Override
    public Result getReport() {
        List<Item> items = itemMapper.selectList(null);
        List<ItemReport> itemReports = new ArrayList<>();
        for (Item item : items) {
            ItemReport itemReport = new ItemReport();
            itemReport.setName(item.getName());
            itemReport.setCount(item.getCount());
            itemReport.setPrice(item.getPrice());
            Integer sellCount = orderItemMapper.selectCountByItemId(item.getId());
            if (sellCount == null) {
                sellCount = 0;
            }
            itemReport.setSell_count(sellCount);
            itemReport.setSell_amount(item.getPrice().multiply(new BigDecimal(itemReport.getSell_count())));
            Integer i = cartMapper.selectCountByItemId(item.getId());
            if (i == null) {
                i = 0;
            }
            itemReport.setCart_sell_count(itemReport.getSell_count() + i);
            itemReports.add(itemReport);
        }
        return Result.ok(itemReports);
    }

    @Override
    public Result getAllOrder() {
        List<Order> orders = orderMapper.selectList(null);
        List<BuyReport> buyReports = new ArrayList<>();
        for (Order order : orders) {
            BuyReport buyReport = new BuyReport();
            buyReport.setOrderId(order.getId());
            buyReport.setName(userMapper.selectById(order.getUserId()).getUsername());
            buyReport.setAmount(order.getAmount());
            if (order.isFinish()) {
                buyReport.setState("已完成");
            } else {
                buyReport.setState("未完成");
            }
            buyReports.add(buyReport);
        }
        return Result.ok(buyReports);
    }
}
