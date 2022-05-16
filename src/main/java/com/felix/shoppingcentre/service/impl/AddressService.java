package com.felix.shoppingcentre.service.impl;

import com.felix.shoppingcentre.entity.Address;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.mapper.AddressMapper;
import com.felix.shoppingcentre.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<Address> findAddressesByUid(Integer uid) {
        List<Address> addressList = addressMapper.findAddressesByUid(uid);
        addressList.forEach(address -> {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        });
        return addressList;
    }

    @Override
    @Transactional
    public void updateUserAddressesToDefault(Address address) {
        Address existAddress = addressMapper.findAddressByAid(address.getAid());
        if (ObjectUtils.isEmpty(existAddress)) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_NOT_FOUND);
        }
        if (!address.getUid().equals(existAddress.getUid())) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_ACCESS_DENIED);
        }
        Integer num = addressMapper.updateUserAddressesToNonDefault(address);
        if (num < 1) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_SETTONODEFAULT_ERROR);
        }
        Integer rows = addressMapper.updateToDefaultByAid(address);
        if (rows != 1) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_SETUP_DEFAULT);
        }
    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {

        Address existAddress = addressMapper.findAddressByAid(aid);
        if (ObjectUtils.isEmpty(existAddress)) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_NOT_FOUND);
        }
        if (!existAddress.getUid().equals(uid)) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_ACCESS_DENIED);
        }
        Integer num = addressMapper.deleteAddressByAid(aid);
        if (num != 1) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_DELETE_ERROR);
        }
        if (existAddress.getPrimaryAddress() == 0) {
            return;
        }
        Address lastModifiedAddress = addressMapper.findLastModified(uid);
        Integer lastModifiedAid = lastModifiedAddress.getAid();
        Address address = new Address();
        address.setAid(lastModifiedAid);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        Integer row = addressMapper.updateToDefaultByAid(address);
        if (row != 1) {
            throw new ServiceException(ExceptionResponseCode.ADDRESS_SETUP_DEFAULT);
        }
    }
}
