package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Address;

import java.util.List;

/**
 * address service
 */
public interface IAddressService {

    void addNewAddress(String username, Address address);

    List<Address> findAddressesByUid(Integer uid);

    void updateUserAddressesToDefault(Address address);

    void deleteAddress(Integer aid, Integer uid, String username);

}
