package com.felix.shoppingcentre.annotations;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentParam {
    @NotEmpty
    private String name;
    @EnumIntValue(enumClass = SexEnum.class, nullable = true)
    private Integer sex;
}
