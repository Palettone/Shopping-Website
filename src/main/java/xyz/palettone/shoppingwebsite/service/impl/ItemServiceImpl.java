package xyz.palettone.shoppingwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Tag;
import xyz.palettone.shoppingwebsite.mapper.ItemMapper;
import xyz.palettone.shoppingwebsite.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public Result getAllItem() {
        List<Item> allItem = itemMapper.selectAll();
        return Result.ok(allItem, (long) allItem.size());
    }

    @Override
    public Result getAllTags(){
        List<Tag> allItem = itemMapper.selectAllTags();

        return Result.ok(allItem, (long) allItem.size());
    }

    @Override
    public Result getItem(String name) {
        List<Item> allItem = itemMapper.selectByName(name);
        return Result.ok(allItem, (long) allItem.size());
    }

    @Override
    public Result insertItem(Item item) {
        if (save(item)){
            return Result.ok("插入成功");
        } else {
            return Result.fail("插入失败");
        }
    }



    //todo 未将item改为tag
    @Override
    public Result insertTag(Item item) {
        if (save(item)){
            return Result.ok("插入成功");
        } else {
            return Result.fail("插入失败");
        }
    }

    @Override
    public Result deleteItem(long id) {
        if (removeById(id)) {
            return Result.ok("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }


    //不确定是否生效
    @Override
    public Result deleteTag(String tag_name) {
        if (removeById(tag_name)) {
            return Result.ok("删除成功");
        } else {
            return Result.fail("删除失败");
        }
    }

    @Override
    public Result modifyItem(Item item) {
        if (updateById(item)) {
            return Result.ok("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }
    @Override
    public Result modifyTag(Tag tag) {
        if (updateById(tag)) {
            return Result.ok("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }
}
