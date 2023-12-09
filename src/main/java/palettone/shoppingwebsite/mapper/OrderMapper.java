package palettone.shoppingwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import palettone.shoppingwebsite.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
