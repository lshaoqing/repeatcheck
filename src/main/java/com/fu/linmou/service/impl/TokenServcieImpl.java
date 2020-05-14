package com.fu.linmou.service.impl;

import com.fu.linmou.config.Constant;
import com.fu.linmou.config.ServiceException;
import com.fu.linmou.enumation.ServiceExceptionEnum;
import com.fu.linmou.service.TokenServcie;
import com.fu.linmou.util.GUIDUtil;
import com.fu.linmou.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/14 09:53
 * @Version: 1.0
 */
@Service
public class TokenServcieImpl implements TokenServcie {


    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String createToken() {
        String token = Constant.TOKEN_KEY + GUIDUtil.getGUID(32);

        try{
            //模拟设置过期时间1000L
            redisUtil.setEx(token,token,1000L);

            return token;
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_HEADER);
        if(StringUtils.isEmpty(token)) {
            token = request.getParameter(Constant.TOKEN_HEADER);
            if(StringUtils.isEmpty(token)) {
                throw new ServiceException(ServiceExceptionEnum.ILLEGAL_ARGUMENT.getMsg(), ServiceExceptionEnum.ILLEGAL_ARGUMENT.getCode());
            }
        }
        //token已存在，判断是否在redis中
        boolean remove = redisUtil.remove(token);
        if(!remove) {
            throw new ServiceException(ServiceExceptionEnum.REPETITIVE_OPERATION.getMsg(), ServiceExceptionEnum.REPETITIVE_OPERATION.getCode());
        }
        return true;
    }
}
