package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.entity.Order;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IOrderService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("/createOrder")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        JsonResult<Order> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        try {
            Order data = orderService.create(aid, cids, uid, username);
            result.setData(data);
        } catch (ServiceException e) {
            log.error("exception when create order, {}, {}",
                    e.getMessageCode(), e.getMessageDetail());
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        return result;
    }

}
