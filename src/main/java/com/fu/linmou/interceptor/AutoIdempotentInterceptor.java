package com.fu.linmou.interceptor;

import com.alibaba.fastjson.JSON;
import com.fu.linmou.annotation.AutoIdempotent;
import com.fu.linmou.service.TokenServcie;
import com.fu.linmou.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author linMou  重复校验
 * @Description: 拦截器
 * @Date: 2020/5/14 10:24
 * @Version: 1.0
 */

public class AutoIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenServcie tokenServcie;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod method = (HandlerMethod)handler;
        AutoIdempotent autoIdempotent = method.getMethodAnnotation(AutoIdempotent.class);
        if(autoIdempotent != null) {
            try{
                // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
                return tokenServcie.checkToken(request);
            }catch (Exception e) {
                CommonResult<Object> error = CommonResult.error(101, e.getMessage());
                //返回接送格式
                writeReturnJson(response, JSON.toJSONString(error));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 返回的json值
     * @param response
     * @param json
     * @throws Exception
     */
    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
