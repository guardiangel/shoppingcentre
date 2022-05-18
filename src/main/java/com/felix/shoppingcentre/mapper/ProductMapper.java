package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
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
