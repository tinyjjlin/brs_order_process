package com.brs.idm.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tiny lin
 * @date 2019/2/21
 */
@Data
public class TokenModel implements Serializable {
    private String  loginName;
    private String token;

    public TokenModel(String loginName , String token){
        this.loginName = loginName;
        this.token = token;
    }


}