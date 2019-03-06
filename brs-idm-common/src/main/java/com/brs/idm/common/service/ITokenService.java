package com.brs.idm.common.service;


import com.brs.idm.common.model.TokenModel;

/**
 * @author tiny lin
 * @date  2018/12/3
*/
public interface ITokenService {

    /**
     *
     * @param loginName
     * @param empNo
     * @return
     */
    public TokenModel generateToken(String loginName, Integer empNo);

    /**
     * 前端获取的token 和redis中token 进行比较
     * @param fromToken
     * @return
     */
    public boolean verifyToken(String fromToken);

    /**
     * 从前端 authentication 获取 token
     * @param authentication
     * @return
     */
    public TokenModel getToken(String authentication);


    /**
     * delete token by loginName
     * @param loginName
     */
    public void deleteToken(String loginName);

    /**
     * 判断用户是否已经登录
     * @param loginName
     * @return
     */
    public boolean  loginValidation(String loginName);
}
