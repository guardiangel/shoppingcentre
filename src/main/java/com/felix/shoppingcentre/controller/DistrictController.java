package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.entity.District;
import com.felix.shoppingcentre.service.IDistrictService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/districts")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;

        @GetMapping("/getDistrict")
    public JsonResult<List<District>> getDistrictByParent(String parent) {
        JsonResult<List<District>> result = new JsonResult<>(ConstantUtils.SUCCESS);
        List<District> districtList = districtService.findByParentCode(parent);
        result.setData(districtList);
        return result;
    }

}
