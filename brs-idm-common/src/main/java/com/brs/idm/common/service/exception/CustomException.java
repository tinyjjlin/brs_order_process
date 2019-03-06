package com.brs.idm.common.service.exception;

/**
 * @author tiny lin
 * @version 1.0
 * @className CustomException
 * @description TODO
 * @date 2018/11/27
 */
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }

}
