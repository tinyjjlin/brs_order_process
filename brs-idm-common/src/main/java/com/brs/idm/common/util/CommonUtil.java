package com.brs.idm.common.util;




import com.brs.idm.common.jwt.JwtSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * @author tiny lin
 * @version 1.0
 * @className CommonUtil
 * @description TODO
 * @date 2018/11/27
 */
public class CommonUtil {
    static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 请求头部保存token的key
     */
    public final static String REQUEST_HEADER_KEY = "Authorization";

    /**
     * 从登录名字中，解析出工号 。
     * rule: username(j1000xx) empno(1000xx)
     * 登录名是由用户名首字母+工号
     * @return
     */
    public static Integer getEmpNo(String userName){
        Integer empNo;
        try {
            empNo = Integer.parseInt(userName.substring(1));
        }catch (Exception e){
            empNo = null;
        }
        return empNo;
    }

    /**
     * 从请求头部获取token
     *
     * @param request
     * @return
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(REQUEST_HEADER_KEY);
    }

    /**
     * 从请求头部获取token，并解析获取登录用户名
     *
     * @param request
     * @return
     */
    public static String getLoginNameFromRequest(HttpServletRequest request) {
        return JwtSupport.getLoginName(getTokenFromRequest(request));
    }

    /**
     * 从请求头部获取token，并解析获取登录员工号
     *
     * @param request
     * @return
     */
    public static Integer getEmployeeNoFromRequest(HttpServletRequest request) {
        return JwtSupport.getEmployeeNo(getTokenFromRequest(request));
    }

    /**
     * 从请求头部获取token，并解析获取员工所有权限列表
     *
     * @param request
     * @return
     */
    public String[] getPermissionCodeListFromRequest(HttpServletRequest request) {
        return JwtSupport.getPermissionCodeList(getTokenFromRequest(request));
    }



}
