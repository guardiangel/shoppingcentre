package com.felix.shoppingcentre.mapper;

import com.felix.shoppingcentre.entity.District;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictMapper {
    List<District> findByParentCode(String parent);

    String findNameByCode(String code);
}
