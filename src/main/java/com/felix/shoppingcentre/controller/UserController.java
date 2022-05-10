package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.aop.anotation.UserLoginAnnotation;
import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IUserService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController{

    @Autowired
    private IUserService userService;

    /**
     * when register a new user, save the log,
     * see {@link com.felix.shoppingcentre.aop.aspect.UserLogAspect} to get more details
     *
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
            return result;
        }
        return result;
    }

    @PostMapping(value = "/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        JsonResult<User> result = new JsonResult<>(ConstantUtils.SUCCESS);
        User user = null;
        try {
            user = userService.login(username, password);
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
            return result;
        }
        result.setData(user);

        //set up session
        session.setAttribute("uid", user.getUid());
        session.setAttribute("username", user.getUsername());

        log.info("uid {}",session.getAttribute("uid"));
        log.info("username {}",session.getAttribute("username"));

        return result;
    }
}
