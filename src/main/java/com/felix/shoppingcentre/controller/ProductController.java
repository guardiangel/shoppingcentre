package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.entity.Product;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IProductService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}/detail")
    public JsonResult<Product> findProductById(@PathVariable("id") Integer id) {
        JsonResult<Product> result = new JsonResult<>(ConstantUtils.SUCCESS);
        try {
            result.setData(productService.findProductById(id));
        } catch (ServiceException e) {
            log.error("exception when searching product, id={}", id);
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        return result;
    }
}
