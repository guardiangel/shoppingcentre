package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressMapper {

    Integer insert(Address address);

    /**
     * @param uid userid
     * @return number of address
     */
    Integer countByUid(Integer uid);


    /**
     * find user's addresses based on userid
     *
     * @param uid userid
     * @return address list
     */
    List<Address> findAddressesByUid(Integer uid);

    /**
     * make a user's addresses to non-default
     *
     * @param address address entity object
     * @return affected rows
     */
    Integer updateToDefaultByAid(Address address);

    /**
     * modify a user's address to default
     *
     * @param address address entity object
     * @return affected rows
     */
    Integer updateUserAddressesToNonDefault(Address address);

    /**
     * @param aid aid
     * @return address entity object
     */
    Address findAddressByAid(Integer aid);

    /**
     * delete an address
     *
     * @param aid aid
     * @return affected rows
     */
    Integer deleteAddressByAid(Integer aid);

    /**
     * get the last modified record
     *
     * @param uid userid
     * @return address entity object
     */
    Address findLastModified(Integer uid);
}
