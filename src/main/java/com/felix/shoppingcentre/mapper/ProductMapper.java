package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> findMostPopularProductList();
}
