package xyz.palettone.shoppingwebsite.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.palettone.shoppingwebsite.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    boolean updateById(int role, String tag, Long id);

    User selectUserByName(String userName);
    User selectUserById(Long userId);

    void resetPsdByName(String userName);
}
