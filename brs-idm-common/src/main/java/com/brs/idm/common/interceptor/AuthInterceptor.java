package com.brs.idm.common.interceptor;


import com.brs.idm.common.annotation.Authentication;
import com.brs.idm.api.IdmService;
import com.brs.idm.api.dto.PrivilegeDTO;
import com.brs.idm.common.annotation.NeedPermissions;
import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.service.ITokenService;
import com.brs.idm.common.util.CommonUtil;
import com.brs.idm.common.util.JsonUtil;
import com.brs.idm.common.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className AuthInterceptor
 * @description TODO
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    static final Logger LOG = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IdmService identityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return handleAuth( request,  response,  handler);
    }

    private boolean  handleAuth(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 对指定controller 进行拦截
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();

            LOG.info("$interceptor controller$ .............................." + method.getName());
            Authentication authentication = method.getAnnotation(Authentication.class);
            NeedPermissions needPermissions = method.getAnnotation(NeedPermissions.class);

            //step 1 : 只针对需要鉴权的annotation
            if (authentication != null || needPermissions != null) {
                LOG.info("$.............1需要访问权限......................$");
                //step 2: 验证 请求头部是否有 Authorization:token;没有返回消息没有权限调用，需要登录！
                String token = request.getHeader(CommonUtil.REQUEST_HEADER_KEY);
                if (StrUtil.isNUll(token)) {
                    return handleIllegalResponse(response, 4004, "token不存在，请重新登录！");
                }
                //step 3: token 验证 签名验证，过期时间验证
                Integer userId = JwtSupport.getEmployeeNo(token);
                if(userId == null){
                    return handleIllegalResponse(response, 404, "token中信息错误，请重新登录！");
                }

                String[] permissionCodes = getPrivCode(userId+"");
                if(permissionCodes != null){
                    for (int i = 0; i < permissionCodes.length; i++) {
                        LOG.info("permission----code" + permissionCodes[i]);
                    }
                }
                // redis 中token 验证
                 if(!tokenService.verifyToken(token)){
                     return handleIllegalResponse(response, 4004, "token验证未通过，请重新登录！");
                 }
                //step 5:判断是否有NeedPermissions 注解
                if (needPermissions != null) {
                    LOG.info("$...............2需要操作权限........................$");
                    if (!StrUtil.matchPermission(needPermissions.value(), permissionCodes, needPermissions.logical())) {
                        return handleIllegalResponse(response, 404, "操作不合法，没有权限访问！");
                    }
                }

            }
        }


        return true;
    }


    /**
     * 获取权限 数值
     * @param userId
     * @return
     */
    public  String[] getPrivCode(String userId) {
        List<PrivilegeDTO> privList = identityService.getPrivilegeByUserId(userId);
        Set<String> privCodeSet = new HashSet<>();
        for (int i = 0; i < privList.size(); i++) {
            String privCode = privList.get(i).getPrivId();
            if (!StringUtils.isEmpty(privCode)) {
                LOG.info("current user permission:=========>" + privCode);
                privCodeSet.add(privCode);
            }
        }
        return privCodeSet.toArray(new String[privCodeSet.size()]);
    }


    /**
     * 处理不合法的操作响应
     *
     * @param response, code, msg
     * @return java.lang.Boolean
     */
    private Boolean handleIllegalResponse(HttpServletResponse response, Integer code, String msg) {
        responseJsonResult(response, code, msg);
        return false;
    }

    private void responseJsonResult(HttpServletResponse response, int code, String msg) {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(JsonUtil.responseJson(code, msg));
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
