package com.felix.shoppingcentre.annotations;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexEnum implements IEnum<Integer> {
    male(0),
    female(1);
    private Integer value;
}
