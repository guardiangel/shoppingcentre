package com.felix.shoppingcentre.annotations;

import java.io.Serializable;

public interface IEnum<T extends Serializable> {
    T getValue();
}
