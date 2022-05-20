package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.ICartService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import com.felix.shoppingcentre.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/carts")
@Slf4j
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    /**
     * @param pid     product id
     * @param amount  product number
     * @param session
     * @return
     */
    @PostMapping("/addToCart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        try {
            cartService.addCart(uid, pid, amount, username);
        } catch (ServiceException e) {
            log.error("error when add to cart, {}, {}",
                    e.getMessageCode(), e.getMessageDetail());
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }

        return result;
    }

    @PostMapping("/getCartVoList")
    public JsonResult<List<CartVo>> findCartVoList(HttpSession session) {
        JsonResult<List<CartVo>> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        List<CartVo> data = cartService.findCartVoByUid(uid);
        result.setData(data);
        return result;
    }
}
