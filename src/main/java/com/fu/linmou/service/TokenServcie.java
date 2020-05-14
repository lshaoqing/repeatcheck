package com.fu.linmou.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linMou
 * @Description: token service
 * @Date: 2020/5/14 09:51
 * @Version: 1.0
 */
public interface TokenServcie {
    /**
     * 创建token
     * @return
     */
    public String createToken();

    /**
     * 校验token
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request);
}
