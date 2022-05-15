package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {

    Integer insert(Address address);

    /**
     * @param uid userid
     * @return number of address
     */
    Integer countByUid(Integer uid);
}
