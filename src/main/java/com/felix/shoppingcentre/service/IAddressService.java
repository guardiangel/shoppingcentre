package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Address;

/**
 * address service
 */
public interface IAddressService {
    void addNewAddress(String username, Address address);
}
