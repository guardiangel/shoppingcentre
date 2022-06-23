package com.felix.shoppingcentre.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class EnumIntValueConstraintValidator implements ConstraintValidator<EnumIntValue, Integer> {

    private boolean nullable;

    private Class<? extends IEnum<Integer>> enumClass;

    private int[] exclusion;

    private Set<Integer> vals;

    /**
     * 初始化校验器，获取注解中的属性并记录下来
     */
    @Override
    public void initialize(EnumIntValue constraintAnnotation) {
        this.nullable = constraintAnnotation.nullable();
        this.enumClass = constraintAnnotation.enumClass();
        this.exclusion = constraintAnnotation.exclusion();

        vals = new HashSet<>();
        IEnum<Integer>[] enumConstants = enumClass.getEnumConstants();
        for (IEnum<Integer> iEnum : enumConstants) {
            vals.add(iEnum.getValue());
        }
        for (int i : exclusion) {
            vals.remove(i);
        }
    }

    /**
     * 校验参数是否合法
     */
    @Override
    public boolean isValid(Integer param, ConstraintValidatorContext context) {
        if (nullable && param == null) {
            return true;
        }

        return vals.contains(param);
    }
}
