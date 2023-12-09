package palettone.shoppingwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import palettone.shoppingwebsite.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectUserByName(String userName);
}
