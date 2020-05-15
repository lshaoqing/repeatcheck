package com.fu.linmou.service;

import com.fu.linmou.vo.UserAddDTO;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 09:29
 * @Version: 1.0
 */
public interface UserService {

    /**
     * 修改
     * @return
     */
    int updateUser();

    /**
     * 新增
     * @param addDTO
     */
    void add(UserAddDTO addDTO);
}
