package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.vo.CartVo;

import java.util.List;

/**
 * cart service interface
 */
public interface ICartService {

    /**
     * add cart
     *
     * @param uid      user id
     * @param pid      product id
     * @param amount   purchase number
     * @param username user's name
     */
    void addCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * find product in cart based on userid
     * @param uid user id
     * @return cartvo list
     */
    List<CartVo> findCartVoByUid(Integer uid);
}
