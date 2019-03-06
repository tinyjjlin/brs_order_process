package com.brs.idm.common.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @className JwtSupport
 * @description TODO
 * @author tiny lin
 * @date  2018/11/27
 * @version 1.0
*/
public class JwtSupport {

    static final Logger LOGGER = LoggerFactory.getLogger(JwtSupport.class);



    /**
     * JWT 生成
     * @return
     */
    public static  String genereateToken(String loginName,Integer empNo){
        //签发时间
        Date iat = new Date();
        //设置签名过期时间  30分钟
        Calendar nowTime=Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, JwtConstant.EXPIRES_MINUTE);
        Date expires = nowTime.getTime();
        // 签名算法
        Algorithm algorithm  = getAlgorithm();

        return JWT.create().withIssuedAt(iat)
                .withExpiresAt(expires)
                .withClaim(JwtConstant.CLAIM_KEY_LOGIN_NMAE,loginName)
                .withClaim(JwtConstant.CLAIM_KEY_EMP_NO, empNo)
                .sign(algorithm);
    }



    /**
     * 获取签名算法
     * @param secret
     * @return
     */
    public static Algorithm  getAlgorithm(String secret){
        return Algorithm.HMAC256(secret);
    }
    /**
     * 获取签名算法
     * @param
     * @return
     */
    public static Algorithm  getAlgorithm(){
        return Algorithm.HMAC256(JwtConstant.SECRET);
    }

    /**
     * token验证（是否过期）
     * @param token
     * @return
     */
   public static Boolean  verifyToken(String token){
        try {
            DecodedJWT verifier = JWT.require(getAlgorithm()).build().verify(token);
            return true;
        }catch (Exception exception) {
            return false;
        }
   }

    /**
     * 获得过期时间
     * @param token
     * @return
     */
    public static Date getExpireTime(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getExpiresAt();

        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 从token中获取登录用户名
      * @param token
     * @return
     */
    public static String getLoginName(String token){
        return getString(token, JwtConstant.CLAIM_KEY_LOGIN_NMAE );
    }

    /**
     * 从token获取员工号
     * @param token
     * @return
     */
    public static Integer getEmployeeNo(String token){
        return getInteger(token, JwtConstant.CLAIM_KEY_EMP_NO);
    }

    /**
     * 从token获取permission code list
     * @param token
     * @return
     */
    public static String[] getPermissionCodeList(String token){
        return getArray(token, JwtConstant.CLAIM_KEY_PERMISSION_CODE_LIST);
    }
    /**
     * 获得字符串
     * @param token
     * @param key
     * @return
     */
    public static String getString(String token,String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取Integer类型数据
     * @param token
     * @param key
     * @return
     */
    public static Integer getInteger(String token,String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asInt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得数组
     * @param token
     * @param key
     * @return
     */
    public static String[] getArray(String token, String key) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(key).asArray(String.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }



}
