package com.hu.libraryManagement.service;

import com.hu.libraryManagement.VO.UserVO;
import com.hu.libraryManagement.dao.IUserDao;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserDao userDao;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TokenService tokenService;

    JSONObject jsonObject = new JSONObject();

    // 向user表中插入用户账号密码
    public boolean insert(UserVO userVO){
        int i = 0;
        if(null != userVO.getUserAccount() && userVO.getUserAccount().length() > 6 && userVO.getUserPassword().length()>5){
            if(null == this.selcet(userVO.getUserAccount())) {
                i = userDao.insert(userVO);
            }
        }

        return i==0 ? false : true;
    }

    // 根据userAccount查找用户账号密码
    public UserVO selcet(String userAccount){
        if(null != userAccount && userAccount.length() > 6){
            UserVO userVO = userDao.select(userAccount);
            //redisTemplate.opsForValue().set(userAccount,userVO);
            return userVO;
        }
        return new UserVO();
    }
    // 根据验证用户账号密码是否正确，实现登录 并生成和返回Token
    public Object login(UserVO userVO){
        if(null != userVO.getUserAccount() && userVO.getUserAccount().length() > 6 && userVO.getUserPassword().length()>5){
            UserVO userVO1 = selcet(userVO.getUserAccount());
            if(userVO.getUserPassword().equals(userVO1.getUserPassword())){
                String token = tokenService.getToken(userVO1);
                jsonObject.put("token", token);
                jsonObject.put("userVO", userVO1);
            }else {
                jsonObject.put("message", "登录失败!");
            }
        }else {
            jsonObject.put("message", "登录失败!");
        }
        return jsonObject;
    }

    public void redis(){
        redisTemplate.opsForValue().set("2h3","userVO");
    }

    public Object redis1(){
        return redisTemplate.opsForValue().get("2h3");
    }

    public void redis3(String s){
        redisTemplate.delete(s);
    }
}

