package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.aop.anotation.UserLoginAnnotation;
import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IUserService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * when register a new user, save the log see {@com.felix.shoppingcentre.aop.aspect.UserLogAspect}
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    @UserLoginAnnotation
    public JsonResult<Void> register(User user) {
        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        try {
            userService.register(user);
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        return result;
    }

}
