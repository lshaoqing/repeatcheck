package com.fu.linmou.constans;

import com.fu.linmou.validator.InArrayValue;

import java.util.Arrays;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 09:46
 * @Version: 1.0
 */
public enum  GenderEnum implements InArrayValue {

    MALE("男",1),
    FEMAIL("女",2);

    private final String name;

    private final Integer value;

    private static final int[] ARRAYS = Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

    GenderEnum(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }
}
