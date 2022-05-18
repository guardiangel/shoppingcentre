package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Product;

import java.util.List;

/**
 * product service interface
 */
public interface IProductService {
    /**
     * find the most five popular products
     * @return
     */
    List<Product> findMostPopularProductList();
}
