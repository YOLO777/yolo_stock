package com.yolo.stock.common.exception.auth;


import com.yolo.stock.common.constant.CommonConstants;
import com.yolo.stock.common.exception.BaseException;

/**
 * Created by ace on 2017/9/10.
 */
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message, CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
