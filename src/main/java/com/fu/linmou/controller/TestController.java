package com.fu.linmou.controller;

import com.fu.linmou.annotation.AutoIdempotent;
import com.fu.linmou.service.TokenServcie;
import com.fu.linmou.util.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/14 10:45
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TokenServcie tokenServcie;

    @RequestMapping("/get/token")
    public CommonResult getToken() {
        String token = tokenServcie.createToken();
       return CommonResult.success(token);
    }

    @AutoIdempotent
    @RequestMapping("idempotent")
    public CommonResult testIdempotent() {
        String data = "10086";
        return CommonResult.success(data);
    }
}
