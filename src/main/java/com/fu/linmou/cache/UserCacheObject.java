package com.fu.linmou.cache;

import lombok.Data;

import java.io.Serializable;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 14:13
 * @Version: 1.0
 */
@Data
public class UserCacheObject  implements Serializable {
    private static final long serialVersionUID = 7994098335003754400L;
    private Integer id;
    private String name;
    private Integer gender;
}