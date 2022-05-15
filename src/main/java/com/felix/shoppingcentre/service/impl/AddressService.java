package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.Address;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.AddressMapper;
import com.felix.shoppingcentre.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Value("${user.address.maxCount}")
    private Integer maxAddressPerUserCount;

    @Override
    public void addNewAddress(String username, Address address) {
        Integer count = addressMapper.countByUid(address.getUid());
        if (!ObjectUtils.isEmpty(count) && count > maxAddressPerUserCount) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_OVER_LIMIT);
        }
        Integer isDefault = count == 0 ? 1 : 0;
        address.setPrimaryAddress(isDefault);
        Date date = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(date);
        address.setModifiedUser(username);
        address.setModifiedTime(date);
        Integer num = addressMapper.insert(address);
        if (num != 1) {
            throw new ServiceException(ExceptionResponseCode.UNKNOW_ERROR);
        }
    }
}
