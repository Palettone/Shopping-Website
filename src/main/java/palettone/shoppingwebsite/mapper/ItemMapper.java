package palettone.shoppingwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import palettone.shoppingwebsite.entity.Item;

import java.util.List;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {
    List<Item> selectAll();
    List<Item> selectByName(String itemName);
}
