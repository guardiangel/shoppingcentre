package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapper {
    /**
     * @param parent parent code
     * @return district list
     */
    List<District> findByParentCode(String parent);

    /**
     * @param code code
     * @return the name of district
     */
    String findNameByCode(String code);
}
