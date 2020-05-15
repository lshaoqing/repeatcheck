package com.fu.linmou.service.impl;

import com.fu.linmou.config.ServiceException;
import com.fu.linmou.service.UserService;
import com.fu.linmou.vo.UserAddDTO;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/14 16:53
 * @Version: 1.0
 */
@Service
@Validated
public class UserServiceImpl implements UserService {

    public void get(@Min(value = 1L, message = "编号必须大于 0") Integer id) {
        System.out.println("我");
    }

    @Override
    public void add(@Valid UserAddDTO addDTO) {
        System.out.println("你");
    }

    public void add01(UserAddDTO addDTO) {
        System.out.println("她");
        this.add(addDTO);
    }

    public void add02(UserAddDTO addDTO) {
        self().add(addDTO);
        System.out.println("他");
    }

    private UserServiceImpl self() {
        return (UserServiceImpl) AopContext.currentProxy();
    }

    @Override
    public int updateUser() {
       throw new ServiceException("是真的难受");
    }
}
