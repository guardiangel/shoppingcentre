package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.Address;
import com.felix.shoppingcentre.entity.Order;
import com.felix.shoppingcentre.entity.OrderItem;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.OrderMapper;
import com.felix.shoppingcentre.service.IAddressService;
import com.felix.shoppingcentre.service.ICartService;
import com.felix.shoppingcentre.service.IOrderService;
import com.felix.shoppingcentre.vo.CartVo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private IAddressService addressService;
    @Autowired
    private ICartService cartService;

    @Override
    @Transactional
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        Date now = new Date();
        List<CartVo> cartVoList = cartService.findCartVoByCids(uid, cids);
        BigInteger totalPrice = new BigInteger("0");

        cartVoList.forEach(cartVo -> {
            totalPrice.add(cartVo.getRealPrice()
                    .multiply(new BigInteger(cartVo.getNum().toString())));
        });

        Order order = new Order();
        order.setUid(uid);
        Address address = addressService.getAddressByAid(aid, uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        //0 means unpaid, should extract this in a public class
        order.setStatus(0);
        order.setOrderTime(now);
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);

        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) {
            throw new ServiceException(ExceptionResponseCode.UNKNOW_ERROR);
        }

        //insert item data to database
        cartVoList.forEach(cartVo -> {
            OrderItem item = new OrderItem();
            item.setOid(order.getOid());
            item.setPid(cartVo.getPid());
            item.setTitle(cartVo.getTitle());
            item.setImage(cartVo.getImage());
            item.setPrice(cartVo.getPrice());
            item.setNum(cartVo.getNum());
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            Integer rowItem = orderMapper.insertOrderItem(item);
            if (rowItem != 1) {
                throw new ServiceException(ExceptionResponseCode.UNKNOW_ERROR);
            }
        });

        return order;
    }
}
