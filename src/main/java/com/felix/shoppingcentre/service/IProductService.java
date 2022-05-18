package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Product;

import java.util.List;

/**
 * product service interface
 */
public interface IProductService {
    /**
     * find the most five popular products
     *
     * @return product list
     */
    List<Product> findMostPopularProductList();

    /**
     * find product by primary key
     *
     * @param id primary key
     * @return product
     */
    Product findProductById(Integer id);
}
