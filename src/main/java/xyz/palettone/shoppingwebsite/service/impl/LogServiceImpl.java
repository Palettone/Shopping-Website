package xyz.palettone.shoppingwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Log;
import xyz.palettone.shoppingwebsite.entity.Tag;
import xyz.palettone.shoppingwebsite.entity.User;
import xyz.palettone.shoppingwebsite.mapper.LogMapper;
import xyz.palettone.shoppingwebsite.mapper.UserMapper;
import xyz.palettone.shoppingwebsite.service.ItemService;
import xyz.palettone.shoppingwebsite.service.LogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Result newLog(String ip_address, Long userId,String type) {
        User user = userMapper.selectById(userId);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        logMapper.insertLog(ip_address,user.getUsername(),type,dateTime.format(formatter));
        return Result.ok("生成日志成功");
    }

    @Override
    public String getIpAddr(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    @Override
    public Result getAllLog() {
        List<Log> allLogs = logMapper.selectAllLogs();
        System.out.println(allLogs);
        return Result.ok(allLogs);
    }
}
