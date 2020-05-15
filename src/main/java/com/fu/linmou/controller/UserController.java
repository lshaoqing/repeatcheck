package com.fu.linmou.controller;

import com.fu.linmou.service.UserService;
import com.fu.linmou.util.CommonResult;
import com.fu.linmou.vo.UserAddDTO;
import com.fu.linmou.vo.UserUpdateDTO;
import com.fu.linmou.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/14 14:29
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块接口")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation(value = "查询用户列表", notes = "接口吧")
    public CommonResult list() {
        List<UserVo> data = new ArrayList<>(2);
        UserVo vo = new UserVo();
        vo.setAge(1);
        vo.setName("小明");
        data.add(vo);
        vo.setName("小红");
        vo.setAge(2);
        data.add(vo);

        return CommonResult.success(data);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "不用太多道理")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name", value = "姓名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name="age", value = "年龄", required = true, dataTypeClass = Integer.class)
    })
    public CommonResult add(@RequestBody UserVo vo) {
        return CommonResult.success(vo);
    }

    @GetMapping("/get/{name}")
    @ApiOperation(value = "根据名称获取", notes = "下雨天八十三")
    @ApiImplicitParam(name = "name", value = "姓名",required = true,dataTypeClass = String.class)
    public CommonResult get(@PathVariable String name) {

        return CommonResult.success(0);
    }


    @GetMapping("/get")
    public CommonResult get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
       return CommonResult.success("成功");
    }

    @PostMapping("/add2")
    public CommonResult add(@Valid @RequestBody UserAddDTO addDTO) {
        userService.add(new UserAddDTO());
        return CommonResult.success("特成功");
    }

    @PostMapping("/update")
    public CommonResult update(@Valid UserUpdateDTO updateGenderDTO) {
        int i = userService.updateUser();
        return CommonResult.success(i);
    }

}
