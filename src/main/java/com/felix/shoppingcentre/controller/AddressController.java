package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.entity.Address;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IAddressService;
import com.felix.shoppingcentre.service.IDistrictService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private IDistrictService districtService;

    @PostMapping("/addNewAddress")
    public JsonResult<Void> addNewAddress(@Valid Address address,
                                          BindingResult bindingResult,
                                          HttpSession session) {

        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.setState(ExceptionResponseCode.DATA_VALIDATION.getCode());
            bindingResult.getAllErrors().forEach(value -> {
                errorMessage.append(value.getDefaultMessage()+"\n");
            });
            result.setMessage(errorMessage.toString());
            return result;
        }

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        address.setUid(uid);
        String provinceName = districtService.findNameByCode(address.getProvinceCode());
        String cityName = districtService.findNameByCode(address.getCityCode());
        String areaName = districtService.findNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);
        try {
            addressService.addNewAddress(username, address);
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        return result;
    }


}
