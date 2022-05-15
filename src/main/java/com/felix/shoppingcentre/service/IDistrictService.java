package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.District;

import java.util.List;

public interface IDistrictService {
    List<District> findByParentCode(String parent);
    String findNameByCode(String code);
}
