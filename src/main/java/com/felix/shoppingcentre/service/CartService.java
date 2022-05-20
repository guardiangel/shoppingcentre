package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Cart;
import com.felix.shoppingcentre.entity.Product;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.CartMapper;
import com.felix.shoppingcentre.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private IProductService productService;

    @Override
    public void addCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findCartByUidAndPid(uid, pid);
        if (ObjectUtils.isEmpty(result)) {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product = productService.findProductById(pid);
            cart.setPrice(new BigInteger(String.valueOf(product.getPrice())));
            cart.setCreatedUser(username);
            Date now = new Date();
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new ServiceException(ExceptionResponseCode.CART_INSERT_ERROR);
            }
        } else {
            Integer cid = result.getCid();
            Integer num = result.getNum() + amount;
            Cart cart = new Cart();
            cart.setCid(cid);
            cart.setNum(num);
            Integer rows = cartMapper.updateCartByCid(cart);
            if (rows != 1) {
                throw new ServiceException(ExceptionResponseCode.CART_UPATE_ERROR);
            }
        }
    }

    @Override
    public List<CartVo> findCartVoByUid(Integer uid) {
        List<CartVo> cartVoList = cartMapper.findCartVoByUid(uid);
        return cartVoList;
    }

}
