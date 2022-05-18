package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.District;

import java.util.List;

/**
 * district service interface
 */
public interface IDistrictService {
    /**
     * find all districts base on parent code
     *
     * @param parent parent code
     * @return district list
     */
    List<District> findByParentCode(String parent);

    /**
     * find district name based on code
     *
     * @param code code
     * @return district name
     */
    String findNameByCode(String code);
}
