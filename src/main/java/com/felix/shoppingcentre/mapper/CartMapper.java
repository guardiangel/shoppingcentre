package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.Cart;
import com.felix.shoppingcentre.vo.CartVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    /**
     * add one cart
     *
     * @param cart cart entity
     * @return affected rows
     */
    Integer insert(Cart cart);

    /**
     * update the number in cart
     *
     * @param cart cart entity
     * @return afftected rows
     */
    Integer updateCartByCid(Cart cart);

    /**
     * find cart by user id and product id
     *
     * @param uid user id
     * @param pid product id
     * @return cart entity
     */
    Cart findCartByUidAndPid(Integer uid, Integer pid);

    /**
     * find all product based on user id
     * @param uid user id
     * @return
     * cartvo list
     */
    List<CartVo> findCartVoByUid(Integer uid);

}
