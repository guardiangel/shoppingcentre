package com.felix.shoppingcentre.service;

import com.felix.shoppingcentre.entity.District;
import com.felix.shoppingcentre.mapper.DistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> findByParentCode(String parent) {
        List<District> districtList = districtMapper.findByParentCode(parent);
        //set id and parent to null
        districtList.forEach(district -> {
            district.setId(null);
            district.setParent(null);
        });
        return districtList;
    }

    @Override
    public String findNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }

}
