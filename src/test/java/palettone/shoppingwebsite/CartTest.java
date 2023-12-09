package palettone.shoppingwebsite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import palettone.shoppingwebsite.mapper.CartMapper;

@SpringBootTest
public class CartTest {
    @Autowired
    CartMapper cartMapper;
    @Test
    public void test(){
        System.out.println(cartMapper.selectCountByItemId(7L));
    }
}
