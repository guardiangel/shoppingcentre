package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.ICartService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import com.felix.shoppingcentre.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
     * @param session session
     * @return JsonResult
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

    @PostMapping("/{cid}/delete")
    public JsonResult<Integer> delete(@PathVariable("cid") Integer cid) {
        JsonResult<Integer> result = new JsonResult<>(ConstantUtils.SUCCESS);
        try {
            cartService.deleteCartByCid(cid);
        } catch (ServiceException e) {
            log.error("exception when delete item by cart id,{}", e);
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }

        return result;
    }

    @PostMapping("/{cid}/addCartNum")
    public JsonResult<Integer> AddCartNum(@PathVariable("cid") Integer cid,
                                          @RequestParam("num") String num,
                                          @RequestParam("operator") String operator,
                                          HttpSession session) {
        JsonResult<Integer> result = new JsonResult<>(ConstantUtils.SUCCESS);
        int number;
        try {
            number = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            log.error("exception when cast String to int,{}", e);
            result.setState(ExceptionResponseCode.DATA_TRANSFER_ERROR.getCode());
            result.setMessage(ExceptionResponseCode.DATA_TRANSFER_ERROR.getMsg());
            return result;
        }

        if (ObjectUtils.isEmpty(operator)
                || ((!"-".equals(operator)) && (!"+".equals(operator)))
                || number <= 0
                || number > Integer.MAX_VALUE) {
            result.setState(ExceptionResponseCode.COMMON_INPUT_ERROR.getCode());
            result.setMessage(ExceptionResponseCode.COMMON_INPUT_ERROR.getMsg());
            return result;
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        try {
            Integer data = cartService.AddCartNum(cid, uid, username, number, operator);
            result.setData(data);
        } catch (ServiceException e) {
            log.error("exception when add cart num, {},{}", e.getMessageCode(), e.getMessageDetail());
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        return result;
    }
}
