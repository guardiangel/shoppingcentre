package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.Address;

import java.util.List;

/**
 * address service interface
 */
public interface IAddressService {

    /**
     * add address
     *
     * @param username username
     * @param address  address
     */
    void addNewAddress(String username, Address address);

    /**
     * find addresses based on userid
     *
     * @param uid userid
     * @return address list
     */
    List<Address> findAddressesByUid(Integer uid);

    /**
     * update a user's all address to default
     *
     * @param address address
     */
    void updateUserAddressesToDefault(Address address);

    /**
     * delete a address
     *
     * @param aid      primary id
     * @param uid      userid
     * @param username username
     */
    void deleteAddress(Integer aid, Integer uid, String username);

    /**
     * find address according to aid
     *
     * @param aid primary key
     * @param uid user id
     * @return address object
     */
    Address getAddressByAid(Integer aid, Integer uid);

}
