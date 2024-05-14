package xyz.palettone.shoppingwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.palettone.shoppingwebsite.entity.Order;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> selectOrderByUserId(Long userId);
}
