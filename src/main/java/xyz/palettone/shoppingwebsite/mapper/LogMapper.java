package xyz.palettone.shoppingwebsite.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Log;
import xyz.palettone.shoppingwebsite.entity.Tag;

import java.util.List;
@Mapper
public interface LogMapper {
    boolean insertLog(String ip_address,String username,String type,String datetime);
    Log selectLogByUsername(String username);
    List<Log> selectAllLogByUsername(String username);
    List<Log> selectAllLogs();
}
