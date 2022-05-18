package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.Product;
import com.felix.shoppingcentre.mapper.ProductMapper;
import com.felix.shoppingcentre.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findMostPopularProductList() {

        List<Product> productList = productMapper.findMostPopularProductList();
        productList.forEach(product -> {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedUser(null);
        });

        return productList;
    }
}
