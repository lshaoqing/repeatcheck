package com.fu.linmou.validator;

import com.fu.linmou.annotation.InEnum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author linMou
 * @Description:
 * @Date: 2020/5/15 10:11
 * @Version: 1.0
 */
public class InEnumValidator implements ConstraintValidator<InEnum,Integer> {

    /**
     * 值数组
     */
    private Set<Integer> values;

    @Override
    public void initialize(InEnum annotation) {
        InArrayValue[] values = annotation.value().getEnumConstants();

        if(values.length == 0) {
            this.values = Collections.emptySet();
        }else {
            this.values = Arrays.stream(values[0].array())
                    .boxed().collect(Collectors.toSet());
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //校验通过
        if(values.contains(value)) {
            return true;
        }
        //校验不通过，自定义提示语句（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        //禁用默认值
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate().replaceAll("\\{value}",values.toString()))
                .addConstraintViolation();
        return false;
    }
}
