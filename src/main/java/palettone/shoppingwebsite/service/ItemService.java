package palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import palettone.shoppingwebsite.dto.Result;
import palettone.shoppingwebsite.entity.Item;

public interface ItemService extends IService<Item> {

    public Result getAllItem();

    Result insertItem(Item item);

    Result deleteItem(long id);

    Result modifyItem(Item item);

    Result getItem(String name);
}
