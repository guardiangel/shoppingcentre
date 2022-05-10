package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.UserMapper;
import com.felix.shoppingcentre.repository.UserRepository;
import com.felix.shoppingcentre.service.IUserService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param user
     */
    @Override

    @Transactional
    public void register(User user) {
        String username = user.getUsername();
        User existUser = userMapper.findByUsername(username);
        /*UserJpaEntity userJpaEntity = userRepository.findByUsername(username);
        User existUser = new User();
        BeanUtils.copyProperties(userJpaEntity, existUser);*/
        if (existUser != null) {
            throw new ServiceException(ExceptionResponseCode.USER_REPEAT);
        }

        Date date = new Date();
        String salt = PasswordUtils.getSalt();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        user.setSalt(salt);
        user.setDelete(0);
        user.setCreatedUser(username);
        user.setCreatedTime(date);
        user.setModifiedUser(username);
        user.setModifiedTime(date);
        Integer num = userMapper.insert(user);
        if (num != 1) {
            throw new ServiceException(ExceptionResponseCode.UNKNOW_ERROR);
        }
    }

    @Override
    public User login(String username, String password) {

        User existUser = userMapper.findByUsername(username);
        if (existUser == null ||existUser.getDelete() == ConstantUtils.USER_DELETED) {
            throw new ServiceException(ExceptionResponseCode.USER_NOT_FOUND);
        }

        String md5Password = getMd5Password(password, existUser.getSalt());
        if (!md5Password.equals(existUser.getPassword())) {
            throw new ServiceException(ExceptionResponseCode.PASSWORD_ERROR);
        }

        User user = new User();

        user.setUid(existUser.getUid());
        user.setUsername(existUser.getUsername());
        user.setAvatar(existUser.getAvatar());

        return user;
    }

    /**
     * encrypt password
     * @param password
     *  password
     * @return
     *  encrypted password
     */
    private String getMd5Password(String password, String ...salt) {
        return PasswordUtils.encode(password, salt[0]);
    }
}
