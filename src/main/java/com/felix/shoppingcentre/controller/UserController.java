package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.aop.anotation.UserLoginAnnotation;
import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IUserService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * user controller
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

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
    public JsonResult<User> login(@RequestParam(value = "username") String username,
                                  @RequestParam(value = "password") String password,
                                  HttpSession session) {
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

        log.info("uid {}", session.getAttribute("uid"));
        log.info("username {}", session.getAttribute("username"));

        return result;
    }

    @PostMapping(value = "/changePassword")
    public JsonResult<Void> changePassword(@RequestParam(value = "oldPassword") String oldPassword,
                                           @RequestParam(value = "newPassword") String newPassword,
                                           HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        try {
            userService.updatePassword(uid, username, oldPassword, newPassword);
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
            return result;
        }
        return result;
    }

    @GetMapping("/getUserById")
    public JsonResult<User> findByUserId(HttpSession session) {
        Integer uid = getUidFromSession(session);
        JsonResult<User> result = new JsonResult<>(ConstantUtils.SUCCESS);
        try {
            result.setData(userService.findByUserId(uid));
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
            return result;
        }
        return result;
    }

    @PostMapping(value = "/updateUserInfo")
    public JsonResult<Void> updateUserInfo(User user, HttpSession session) {
        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        try {
            userService.updateUserInfo(user);
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        return result;
    }

}
