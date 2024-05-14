package xyz.palettone.shoppingwebsite;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.palettone.shoppingwebsite.controller.LogController;
import xyz.palettone.shoppingwebsite.mapper.CartMapper;
import xyz.palettone.shoppingwebsite.mapper.ItemMapper;
import xyz.palettone.shoppingwebsite.service.LogService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

import static com.baomidou.mybatisplus.extension.toolkit.Db.list;

@SpringBootTest

public class LogTest {
    @Autowired
    LogController logController;
    @Autowired
    LogService logService;

    @Test
    public void test1(){
        logService.newLog("000.000.000.000",1L,"login");
    }
    @Test
    public void test2(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();


        logController.newLog(request,1L,"login");
    }
}
