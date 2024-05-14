package xyz.palettone.shoppingwebsite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.palettone.shoppingwebsite.mapper.CartMapper;
import xyz.palettone.shoppingwebsite.mapper.ItemMapper;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@SpringBootTest
public class AdminTest {
    @Autowired
    ItemMapper itemMapper;

    @Test
    public void test2(){
        itemMapper.removeByName("abba");
        //itemMapper.save("abba");
    }
}
