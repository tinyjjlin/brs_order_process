package com.brs.idm.common.model;

import lombok.Data;

/**
 * @author tiny lin
 * @date 2019/2/20
 */
@Data
public class RestResult {
    private Integer code;
    private String msg;
    private Object data;

    public RestResult(Integer code,String message,Object data){
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public RestResult(Integer code,String message){
        this(code,message,null);
    }
}
