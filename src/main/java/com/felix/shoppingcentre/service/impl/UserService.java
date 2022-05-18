package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.entity.UserJpaEntity;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.UserMapper;
import com.felix.shoppingcentre.repository.UserRepository;
import com.felix.shoppingcentre.service.IUserService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.PasswordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void register(User user) {
        String username = user.getUsername();
        User existUser = userMapper.findByUsername(username);
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
        if (ObjectUtils.isEmpty(existUser) || existUser.getDelete() == ConstantUtils.USER_DELETED) {
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
     * use jpa update password
     *
     * @param uid              userid
     * @param username         username
     * @param originalPassword original password
     * @param newPassword      new passwrod
     */
    @Override
    public void updatePassword(Integer uid, String username,
                               String originalPassword, String newPassword) {

        Optional<UserJpaEntity> existUserJpaEntityOptional = userRepository.findById(uid);

        if (existUserJpaEntityOptional.isEmpty()) {
            throw new ServiceException(ExceptionResponseCode.USER_NOT_FOUND);
        }

        UserJpaEntity existUserJpaEntity = existUserJpaEntityOptional.get();

        if (existUserJpaEntity.getDelete() == ConstantUtils.USER_DELETED) {
            throw new ServiceException(ExceptionResponseCode.USER_DELETED);
        }

        String oldMd5Password = getMd5Password(originalPassword, existUserJpaEntity.getSalt());
        if (!oldMd5Password.equals(existUserJpaEntity.getPassword())) {
            throw new ServiceException(ExceptionResponseCode.PASSWORD_INPUT_WRONG);
        }

        String newMd5Password = getMd5Password(newPassword, existUserJpaEntity.getSalt());

        if (newMd5Password.equals(existUserJpaEntity.getPassword())) {
            throw new ServiceException(ExceptionResponseCode.PASSWORD_EQUAL_ORIGINAL);
        }

        UserJpaEntity userJpa = new UserJpaEntity();
        BeanUtils.copyProperties(existUserJpaEntity, userJpa);

        userJpa.setPassword(newMd5Password);
        userJpa.setModifiedTime(new Date());
        userJpa.setModifiedUser(username);

        UserJpaEntity newUserJpa = userRepository.save(userJpa);
        if (ObjectUtils.isEmpty(newUserJpa)) {
            throw new ServiceException(ExceptionResponseCode.UNKNOW_ERROR);
        }

    }

    @Override
    public User findByUserId(Integer uid) {

        User existUser = userMapper.findByUid(uid);

        if (ObjectUtils.isEmpty(existUser)) {
            throw new ServiceException(ExceptionResponseCode.USER_NOT_FOUND);
        }
        if (existUser.getDelete() == ConstantUtils.USER_DELETED) {
            throw new ServiceException(ExceptionResponseCode.USER_DELETED);
        }

        User user = new User();
        user.setUid(uid);
        user.setUsername(existUser.getUsername());
        user.setPhone(existUser.getPhone());
        user.setEmail(existUser.getEmail());
        user.setGender(existUser.getGender());

        return user;
    }

    @Override
    public void updateUserInfo(User user) {

        User existUser = userMapper.findByUid(user.getUid());
        if (ObjectUtils.isEmpty(existUser)) {
            throw new ServiceException(ExceptionResponseCode.USER_NOT_FOUND);
        }
        if (existUser.getDelete() == ConstantUtils.USER_DELETED) {
            throw new ServiceException(ExceptionResponseCode.USER_DELETED);
        }
        Integer rows = userMapper.updateUserInfo(user);
        if (rows != 1) {
            throw new ServiceException(ExceptionResponseCode.USER_UPDATE_ERROR);
        }

    }

    @Override
    public void updateUserAvatar(User user) {
        User existUser = userMapper.findByUid(user.getUid());
        if (ObjectUtils.isEmpty(existUser)) {
            throw new ServiceException(ExceptionResponseCode.USER_NOT_FOUND);
        }
        if (existUser.getDelete() == ConstantUtils.USER_DELETED) {
            throw new ServiceException(ExceptionResponseCode.USER_DELETED);
        }
        Integer rows = userMapper.updateUserAvatar(user);
        if (rows != 1) {
            throw new ServiceException(ExceptionResponseCode.USER_UPDATE_ERROR);
        }
    }

    /**
     * encrypt password
     *
     * @param password password
     * @return encrypted password
     */
    private String getMd5Password(String password, String... salt) {
        return PasswordUtils.encode(password, salt[0]);
    }
}
