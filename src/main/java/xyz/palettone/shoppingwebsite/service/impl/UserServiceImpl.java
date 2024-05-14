package xyz.palettone.shoppingwebsite.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.palettone.shoppingwebsite.VO.ItemReport;
import xyz.palettone.shoppingwebsite.dto.Result;
import xyz.palettone.shoppingwebsite.entity.*;
import xyz.palettone.shoppingwebsite.mapper.ItemMapper;
import xyz.palettone.shoppingwebsite.mapper.OrderItemMapper;
import xyz.palettone.shoppingwebsite.mapper.OrderMapper;
import xyz.palettone.shoppingwebsite.mapper.UserMapper;
import xyz.palettone.shoppingwebsite.mapper.LogMapper;
import xyz.palettone.shoppingwebsite.service.UserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;


import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.*;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
@Autowired
private ItemMapper itemMapper;
    @Autowired
    private LogMapper logMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public Result modifyUser(User user) {
        if (userMapper.updateById(user.getRole(),user.getTag(),user.getId())) {
            return Result.ok("修改成功");
        } else {
            return Result.fail("修改失败");
        }
    }

    @Override
    public Result login(User user, HttpServletResponse response) {
        User u = userMapper.selectUserByName(user.getUsername());
        if (u != null) {
            if (u.getPassword().equals(user.getPassword())) {
                response.addCookie(new Cookie("user" , u.getId().toString()));
                Result ok = Result.ok();
                ok.setErrorMsg(String.valueOf(u.getRole()));
                return ok;
            } else {
                return Result.fail("密码错误");
            }
        } else {
            return Result.fail("用户不存在");
        }
    }

    @Override
    public Result register(User user) {
        User u = userMapper.selectUserByName(user.getUsername());
        if (u != null) {
            return Result.fail("用户已存在");
        } else {
            user.setBalance(new BigDecimal("100"));
            boolean b = save(user);
            if (b) {
                return Result.ok("注册成功");
            } else {
                return Result.fail("注册失败");
            }
        }
    }

    @Override
    public Result getUserById(Long userId) {
        User u=userMapper.selectUserById(userId);

        return Result.ok(u);
    }

    @Override
    public Result getAddress(String username) {
        Log log=logMapper.selectLogByUsername(username);
        String ip= log.getIpAddress();
        if (ip.equals("127.0.0.1")) {
            return Result.ok("内网IP");
        }
        if (true) {
            try {
                String rspStr = sendGet("http://whois.pconline.com.cn/ipJson.jsp", "ip=" + ip + "&json=true" ,"GBK");
                if (StringUtils.isEmpty(rspStr)) {
                    return Result.fail("获取地理位置异常 " );
                }
                JSONObject obj = JSONObject.parseObject(rspStr);
                String region = obj.getString("pro");
                String city = obj.getString("city");
                return Result.ok(city);
            } catch (Exception e) {
                return Result.fail("获取地理位置异常 " );
            }
        }
        return null;
    }
    public String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;

            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept" , "*/*");
            connection.setRequestProperty("connection" , "Keep-Alive");
            connection.setRequestProperty("user-agent" , "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    @Override
    public Result getWealth(String username) {
        BigDecimal money=new BigDecimal(0);
        String wealth;
        User user=userMapper.selectUserByName(username);
        List<Order> Orders = orderMapper.selectOrderByUserId(user.getId());

        for (Order order : Orders) {
//            OrderItem orderItem = orderItemMapper.selectById(order.getId());
//            Item item=itemMapper.selectById(orderItem.getItemId());
            //amount?? count??
            money=money.add(order.getAmount()) ;

        }
        if(money.equals(new BigDecimal(0))){
            wealth="weak";
        }
        else if(money.compareTo(new BigDecimal(100))!=1){
            wealth="middle";
        }
        else{
            wealth="strong";
        }
        return Result.ok(wealth);
    }

    @Override
    public Result getFavouriteTag(String username) {

        String favourite_tag="Technical";
        Integer visit_time=0;
        HashMap<String,Integer> hashMap = new HashMap<>();
        List<Log> logs = logMapper.selectAllLogByUsername(username);
        List<Tag> tags = itemMapper.selectAllTags();
        Set<String> tagSet=new HashSet<>();
        for(Tag tag:tags){
            if(tag.getTag()!="All"){
                tagSet.add(tag.getTag());//所有除了All以外的商品标签
            }

        }
        for(Log log:logs){
            if(tagSet.contains(log.getAction())){//过滤掉操作行为,只保留标签查询
                if(hashMap.containsKey(log.getAction())){
                    hashMap.put(log.getAction(),hashMap.get(log.getAction())+1);
                    if(hashMap.get(log.getAction())>visit_time){//维护最大访问次数
                        visit_time=hashMap.get(log.getAction());
                        favourite_tag = log.getAction();
                    }
                }else{
                    hashMap.put(log.getAction(),1);
                }
            }

        }
        return Result.ok(favourite_tag);
    }

    @Override
    public Result getUsers() {
        return Result.ok(userMapper.selectList(null));
    }

    @Override
    public Result resetPsd(String username) {
        userMapper.resetPsdByName(username);
        return Result.ok("重置成功");
    }
}
