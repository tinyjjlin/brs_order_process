package com.brs.idm.common.service;



import com.brs.idm.common.jwt.JwtSupport;
import com.brs.idm.common.model.TokenModel;
import com.brs.idm.common.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author tiny lin
 * @date  2018/12/3
*/
@Service
public class TokenServiceImpl implements ITokenService {

    Logger logger =LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String,String> redisTemplate;



    @Override
    public TokenModel generateToken(String loginName, Integer empNo) {
        String token = JwtSupport.genereateToken(loginName,empNo);
        TokenModel tokenModel = new TokenModel(loginName,token);
        //2 save to redis and set expire time
        redisTemplate.boundValueOps(loginName).set(token);
        redisTemplate.boundValueOps(loginName).expire(30, TimeUnit.MINUTES);

        String loginStatus = loginName+"_loginStatus";
        redisTemplate.boundValueOps(loginStatus).set("1");
        redisTemplate.boundValueOps(loginStatus).expire(30, TimeUnit.MINUTES);



        logger.info("## save token to redis.........................................");
        return tokenModel;
    }

    @Override
    public boolean loginValidation(String loginName) {
        return redisTemplate.hasKey(loginName)?true:false;
    }

    @Override
    public boolean verifyToken(String fromToken) {
        if (StringUtils.isEmpty(fromToken)) {
            return false;
        }
        try{
            //获取 保持在 token 中的用户信息
            String userName= JwtSupport.getLoginName(fromToken);
            //jwt token 签名验证，过期时间验证
//           if( !JwtSupport.verifyToken(fromToken)){
//               return false;
//           }
            logger.info("from client ----------get the token -------username:"+userName);
            TokenModel tokenModel = new TokenModel(userName,fromToken);
           String token = redisTemplate.boundValueOps(tokenModel.getLoginName()).get();
          //  String loginStatus = userName+"_loginStatus";
         //  String loginStatusValue =  redisTemplate.boundValueOps(loginStatus).get();

            //redis 中token比对
            if (token == null || !token.equals(tokenModel.getToken())) {
                return false;
            }

            //check success add expire time
            redisTemplate.boundValueOps(tokenModel.getLoginName()).expire(15, TimeUnit.MINUTES);
            return true;

        }catch (Exception e ){
            logger.info(e.getMessage());
            return  false; }


    }

    @Override
    public TokenModel getToken(String authentication) {
        return null;
    }

    @Override
    public void deleteToken(String userName) {
        logger.info("##......redis delete token.............................");
        redisTemplate.delete(userName);

    }
}
