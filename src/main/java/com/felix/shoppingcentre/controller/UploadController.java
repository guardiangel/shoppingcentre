package com.felix.shoppingcentre.controller;

import com.felix.shoppingcentre.entity.User;
import com.felix.shoppingcentre.exception.ExceptionResponseCode;
import com.felix.shoppingcentre.exception.ServiceException;
import com.felix.shoppingcentre.service.IUserService;
import com.felix.shoppingcentre.utils.ConstantUtils;
import com.felix.shoppingcentre.utils.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * upload file controller
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class UploadController extends BaseController {

    @Autowired
    private IUserService userService;

    @Value("${spring.servlet.multipart.max-file-size}")
    private Integer AVATAR_MAX_SIZE;

    public static final List<String> avatarListType
            = new ArrayList<String>(Arrays.asList("image/jpeg", "image/jpg",
            "image/png", "image/bmp", "image/gif"));

    @RequestMapping("/upload.do")
    @ResponseBody
    public JsonResult<String> upload(HttpServletRequest request,
                         @RequestParam("file") MultipartFile file,
                         HttpSession session) {

        JsonResult<String> result = new JsonResult<>(ConstantUtils.SUCCESS);

        if (file.isEmpty()) {
            throw new ServiceException(ExceptionResponseCode.FILE_NULL);
        }
        if (file.getSize() > Integer.valueOf(AVATAR_MAX_SIZE)) {
            throw new ServiceException(ExceptionResponseCode.FILE_OVER_MAX_SIZE);
        }
        String contentType = file.getContentType();
        if (!avatarListType.contains(contentType)) {
            StringBuilder stringBuilder = new StringBuilder("not accepted file type, accepted types are:");
            avatarListType.forEach((value) -> {
                stringBuilder.append(value).append("\n");
            });
            throw new ServiceException(ExceptionResponseCode.FILE_TYPE_ERROR.getCode(), stringBuilder.toString());
        }

        // get original file name
        String originalFilename = file.getOriginalFilename();
        // get context path
        String realPath = request.getServletContext().getRealPath("upload");
        // create file
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        // designate file name
        String fileName = UUID.randomUUID().toString();
        // get extensible suffix
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String fullFilename = fileName + suffix;
        try {
            file.transferTo(new File(dir, fullFilename));
        } catch (IllegalStateException e) {
            throw new ServiceException(ExceptionResponseCode.FILE_STATE_ABNORMAL);
        } catch (IOException e) {
            throw new ServiceException(ExceptionResponseCode.FILE_UPLOAD_ERROR);
        }

        String avatar = "/upload/" + fullFilename;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        User user = new User();
        user.setUid(uid);
        user.setAvatar(avatar);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());
        try {
            userService.updateUserAvatar(user);
        } catch (ServiceException e) {
            result.setState(e.getMessageCode());
            result.setMessage(e.getMessageDetail());
        }
        result.setData(avatar);
        return result;
    }
}