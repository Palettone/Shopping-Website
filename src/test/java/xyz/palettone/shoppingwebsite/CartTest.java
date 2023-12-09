package xyz.palettone.shoppingwebsite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.palettone.shoppingwebsite.mapper.CartMapper;

import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@SpringBootTest
public class CartTest {
    @Autowired
    CartMapper cartMapper;
    @Test
    public void test(){
        System.out.println(cartMapper.selectCountByItemId(7L));
    }
    @Test
    public void test2(){
        List<Long> longs = List.of(9L,10L,12L,8L);
        String string = longs.toString();
        string = string.substring(1, string.length() - 1);
        System.out.println(string);
        cartMapper.queryUsersBatch(4,string);
    }
}
