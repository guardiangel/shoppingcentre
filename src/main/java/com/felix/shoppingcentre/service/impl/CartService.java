package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.Cart;
import com.felix.shoppingcentre.entity.Product;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.CartMapper;
import com.felix.shoppingcentre.service.ICartService;
import com.felix.shoppingcentre.service.IProductService;
import com.felix.shoppingcentre.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        return cartMapper.findCartVoByUid(uid);
    }

    @Override
    public Integer AddCartNum(Integer cid, Integer uid, String username, Integer number, String operator) {
        Cart cart = cartMapper.findCartByCid(cid);
        if (ObjectUtils.isEmpty(cart)) {
            throw new ServiceException(ExceptionResponseCode.CART_NOT_FOUND);
        }
        if (!uid.equals(cart.getUid())) {
            throw new ServiceException(ExceptionResponseCode.CART_ACCESS_DENIED);
        }

        if (!number.equals(cart.getNum())) {
            throw new ServiceException(ExceptionResponseCode.PRODUCT_NUM_ERROR);
        }

        if ("+".equals(operator)) {
            number++;
        } else {
            number--;
        }
        number = number < 0 ? 0 : number;
        //Integer number = cart.getNum() + 1;
        Date now = new Date();
        Cart newCart = new Cart();
        newCart.setCid(cid);
        newCart.setNum(number);
        newCart.setModifiedUser(username);
        newCart.setModifiedTime(now);
        Integer rows = cartMapper.updateCartByCid(newCart);
        if (rows != 1) {
            throw new ServiceException(ExceptionResponseCode.CART_UPATE_ERROR);
        }
        return number;
    }

    @Override
    public void deleteCartByCid(Integer cid) {
        Integer row = cartMapper.deleteCartByCid(cid);
        if (row != 1) {
            throw new ServiceException(ExceptionResponseCode.CART_DELETE_ERROR);
        }
    }

    @Override
    public List<CartVo> findCartVoByCids(Integer uid, Integer[] cids) {
        List<CartVo> list = cartMapper.findCartVoByCids(cids);
        List<CartVo> result = null;
        if (!ObjectUtils.isEmpty(list)) {
            result = list.stream().
                    filter(cartVo -> cartVo.getUid().equals(uid)).collect(Collectors.toList());
        }
        return result;
    }

}
