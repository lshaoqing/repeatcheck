package com.fu.linmou.vo;

import com.fu.linmou.annotation.InEnum;
import com.fu.linmou.constans.GenderEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 10:32
 * @Version: 1.0
 */
@Data
public class UserUpdateDTO {
    /**
     * 用户编号
     */
    @NotNull(message = "用户编号不能为空")
    private Integer id;

    /**
     * 性别
     */
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别必须是 {value}")
    private Integer gender;
}
