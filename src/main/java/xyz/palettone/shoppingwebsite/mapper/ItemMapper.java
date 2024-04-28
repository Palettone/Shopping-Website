package xyz.palettone.shoppingwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Tag;

import java.util.List;

@Mapper
public interface ItemMapper extends BaseMapper<Item> {
    List<Item> selectAll();

    List<Tag> selectAllTags();
    List<Item> selectByName(String itemName);
}
