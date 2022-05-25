package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Order;

/**
 * order service interface
 */
public interface IOrderService {
    /**
     * create order
     *
     * @param aid      address id
     * @param cids     cart ids
     * @param uid      user id
     * @param username user name
     * @return order object
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);
}
