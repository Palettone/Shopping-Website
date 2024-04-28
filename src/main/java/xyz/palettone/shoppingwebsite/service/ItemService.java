package xyz.palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Tag;

public interface ItemService extends IService<Item> {

    public Result getAllItem();

    public Result getAllTags();

    Result insertItem(Item item);
//    Result insertTag(Tag tag);


    Result deleteItem(long id);

    Result deleteTag(String tag_name);

    Result modifyItem(Item item);

//    Result modifyTag(Tag tag);

    Result getItem(String name);
}
