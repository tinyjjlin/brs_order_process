package com.brs.order.rest.api;

import com.brs.idm.api.IdmService;
import com.brs.idm.api.RoleConstant;
import com.brs.idm.api.User;
import com.brs.idm.api.dto.UserInfoDTO;
import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.model.RestResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tiny lin
 * @date 2019/2/28
 */
@RestController
@Api("订单系统用户信息查询api")
public class OrderUserController {

    @Autowired
    private IdmService identityService;

    @GetMapping("/api/order/editorList")
    public RestResult editorList() {
        List<User> userList = identityService.getUserWithRole(RoleConstant.ROLE_EDITOR);
        return new RestResult(2000, "editor list ", userList);
    }

    /**
     * login user info
     * @param token
     * @return
     */
    @ApiOperation("get login user info")
    @GetMapping("/api/order/userInfo")
    @Authentication
    public RestResult  getUserInfo(@RequestHeader("Authorization")String token){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String userId = empNo+"";
        UserInfoDTO loginUserDto = identityService.getLoginUserInfo(userId);
        if(loginUserDto != null){
            return new RestResult(2000,"get login user inf success",loginUserDto );
        }else{
            return new RestResult(4001, "get login user inf failed");
        }
    }
}



