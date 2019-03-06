package com.brs.idm.rest.api.login;
import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.model.RestResult;
import com.brs.idm.common.model.TokenModel;
import com.brs.idm.common.service.AdService;
import com.brs.idm.common.service.ITokenService;
import com.brs.idm.common.util.CommonUtil;
import com.brs.idm.common.util.StrUtil;

import com.brs.idm.api.IdmService;
import com.brs.idm.api.vo.LoginVO;
import com.brs.idm.api.dto.UserInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tiny lin
 * @date 2018/11/29
 */
@RestController
@Api
public class LoginController {
    private final static String USER_TYPE_CLIENT ="client";
    private final static String USER_TYPE_STAFF = "staff";
    static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private AdService ldapService;
    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IdmService identityService;

    @PostMapping("/api/idm/client/login")
    @ApiOperation(value = "client login")
    public RestResult clientLogin(HttpServletRequest request, @RequestBody LoginVO loginUserDto){
        UserInfoDTO loginUserInfo = identityService.getLoginUserInfo(loginUserDto.getUserName());
       if(loginUserInfo != null){
           return new RestResult(2000,"client login success" );
       }else{
           return new RestResult(4001,"client login failed" );
       }
    }

    @PostMapping("/api/idm/staff/login")
    @ApiOperation(value = "brs staff login")
    public RestResult login(HttpServletRequest request, @RequestBody LoginVO loginVO){
        try {
            // step1: ldap 用户认证
            //ldapService.authentication(loginUserDto);
            //step2 :验证工号存在
            Integer empNo = CommonUtil.getEmpNo(loginVO.getUserName());
            if(empNo == null){
                return new RestResult(4001, "登录账号必须是姓名首字母+工号！", null);
            }
            System.out.println("login..................."+empNo);
            //step3:验证mysql用户是否创建
            UserInfoDTO userInfoDTO = identityService.getLoginUserInfo(empNo+"");
            if( userInfoDTO == null){
                return new RestResult(4001, "新员工没有在系统中注册！", null);
            }
            //step4:生成token
            TokenModel tokenModel = tokenService.generateToken(loginVO.getUserName(),empNo);
            //step5:保持用户的登录ip地址
            Map<String,Object> resultMap = new HashMap <>(2);
            resultMap.put("token",tokenModel.getToken() );
            resultMap.put("userInfo",userInfoDTO );

            return new RestResult(2000, "登录成功!", resultMap);
        } catch (Exception e) {
            return new RestResult(4001, e.getMessage(),null);
        }
    }

    @GetMapping("/api/idm/logout")
    @ApiOperation(value = "logout")
    @Authentication
    public RestResult logout(@RequestHeader("Authorization")String token){
        String loginName = JwtSupport.getLoginName(token);
        tokenService.deleteToken(loginName);
        return new RestResult(2000, "退出操作成功!");
    }

    /**
     * login user info
     * @param token
     * @return
     */
    @ApiOperation("get login user info")
    @GetMapping("/api/idm/userInfo")
    @Authentication
    public RestResult  getLoginUserInfo(@RequestHeader("Authorization")String token){
        Integer empNo = JwtSupport.getEmployeeNo(token);
        String userId = empNo+"";
        UserInfoDTO loginUserDto = identityService.getLoginUserInfo(userId);
        if(loginUserDto != null){
            return new RestResult(2000,"get login user inf success",loginUserDto );
        }else{
            return new RestResult(4001, "get login user inf failed");
        }
    }

    @PostMapping("/api/idm/loginValidation")
    @ApiOperation("login validation")
    @Authentication
    public RestResult loginValidation(HttpServletRequest request, @RequestBody LoginVO loginDto){
        if(loginDto == null || StrUtil.isNUll(loginDto.getUserName())){
            return new RestResult(2500, "该账号没有登录！",null);
        }
        if(!tokenService.loginValidation(loginDto.getUserName())){
            return new RestResult(2500, "该账号没有登录", null);
        }else{
            Integer empNo = CommonUtil.getEmpNo(loginDto.getUserName());
            String loginAddress = "";
            return new RestResult(4510, "该账号"+loginDto.getUserName()+"已经在"+loginAddress+"登录,是否继续登录？", null);
        }
    }

}