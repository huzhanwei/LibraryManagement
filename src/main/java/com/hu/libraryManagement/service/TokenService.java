package com.hu.libraryManagement.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hu.libraryManagement.VO.UserVO;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;

/***
 * token 下发
 */
@Service
public class TokenService {

    public String getToken(UserVO userVo) {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;//一小时有效时间
        Date end = new Date(currentTime);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        String token = "";

        /**
         * withHeader http头部信息
         * withAudience 用户登录（user）信息   在Token拦截器（AuthenticationInterceptor）中通过JWT.decode(token).getAudience().get(0)提取
         * withIssuedAt(start) withExpiresAt(end) 有效时间
         * sign Algorithm.私钥及加密算法
         * */
        token = JWT.create().withHeader(header).withAudience(userVo.getUserAccount()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(userVo.getUserPassword()));  // Algorithm. 私钥及加密算法
        return token;
    }
}

