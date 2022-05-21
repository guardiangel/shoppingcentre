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
     *
     * @param uid user id
     * @return cartvo list
     */
    List<CartVo> findCartVoByUid(Integer uid);

    /**
     * add product to cart
     *
     * @param cid      cart id
     * @param uid      user id
     * @param username user name
     * @param number product number
     * @param operator add or minus
     * @return affected rows
     */
    Integer AddCartNum(Integer cid, Integer uid, String username, Integer  number, String operator);

    /**
     * delete item by cart id
     *
     * @param cid cart id
     */
    void deleteCartByCid(Integer cid);

    /**
     * find all items by ids of cart
     * @param uid userid
     * @param cids ids of cart
     * @return
     * cartvo list
     */
    List<CartVo> findCartVoByCids(Integer uid, Integer[] cids);
}
