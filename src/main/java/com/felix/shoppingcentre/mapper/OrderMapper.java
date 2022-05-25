package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.Order;
import com.felix.shoppingcentre.entity.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * order dao layer
 */
@Repository
public interface OrderMapper {
    /**
     * add order record
     *
     * @param order order object
     * @return affected rows
     */
    Integer insertOrder(Order order);

    /**
     * add product in order
     *
     * @param orderItem specified item
     * @return affected rows
     */
    Integer insertOrderItem(OrderItem orderItem);

}
