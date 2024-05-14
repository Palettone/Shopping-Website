package xyz.palettone.shoppingwebsite.service;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.Item;
import xyz.palettone.shoppingwebsite.entity.Tag;
public interface LogService {
    Result newLog(String ip_address,Long userId,String type);

    String getIpAddr(HttpServletRequest request);

    Result getAllLog();
}
