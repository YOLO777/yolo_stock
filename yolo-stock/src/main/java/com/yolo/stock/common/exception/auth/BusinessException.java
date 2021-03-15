package com.yolo.stock.common.exception.auth;

import com.yolo.stock.common.constant.CommonConstants;
import com.yolo.stock.common.exception.BaseException;

public class BusinessException extends BaseException {
    public BusinessException(String message) {
        super(message, CommonConstants.EX_OTHER_CODE);
    }
}
