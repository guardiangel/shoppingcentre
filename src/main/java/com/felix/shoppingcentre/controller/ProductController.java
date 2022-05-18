package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.entity.Product;
import com.felix.shoppingcentre.service.IProductService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @GetMapping("/findMostPopularProductList")
    public JsonResult<List<Product>> findMostPopularProductList() {
        JsonResult<List<Product>> result = new JsonResult<>(ConstantUtils.SUCCESS);
        List<Product> data = productService.findMostPopularProductList();
        result.setData(data);
        return result;
    }
}
