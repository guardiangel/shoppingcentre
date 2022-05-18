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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/address")
@Slf4j
public class AddressController extends BaseController {

    @Autowired
    private IAddressService addressService;
    @Autowired
    private IDistrictService districtService;

    @GetMapping("/getAddressList")
    public JsonResult<List<Address>> getAddressesByUid(HttpSession session) {

        JsonResult<List<Address>> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        List<Address> addressList = addressService.findAddressesByUid(uid);
        result.setData(addressList);

        return result;
    }

    @PostMapping("/addNewAddress")
    public JsonResult<Void> addNewAddress(@Valid Address address,
                                          BindingResult bindingResult,
                                          HttpSession session) {

        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            result.setState(ExceptionResponseCode.DATA_VALIDATION.getCode());
            bindingResult.getAllErrors().forEach(value -> errorMessage.append(value.getDefaultMessage()).append("\n"));
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
            log.error("exception when add a new address, {},{}",
                    e.getMessageCode(), e.getMessageDetail());
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }

        return result;
    }

    @PostMapping("/{aid}/setDefault")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {

        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Address address = new Address();
        address.setAid(aid);
        address.setUid(uid);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        try {
            addressService.updateUserAddressesToDefault(address);
        } catch (ServiceException e) {
            log.error("exception when set user's address to default, {},{}",
                    e.getMessageCode(), e.getMessageDetail());
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }

        return result;
    }

    @PostMapping("/{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid, HttpSession session) {

        JsonResult<Void> result = new JsonResult<>(ConstantUtils.SUCCESS);
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        try {
            addressService.deleteAddress(aid, uid, username);
        } catch (ServiceException e) {
            log.error("exception when delete address {},{}",
                    e.getMessageCode(), e.getMessageDetail());
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }

        return result;
    }

}
