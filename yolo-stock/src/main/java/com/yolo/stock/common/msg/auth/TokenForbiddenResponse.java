package com.yolo.stock.common.msg.auth;


import com.yolo.stock.common.constant.RestCodeConstants;
import com.yolo.stock.common.msg.BaseResponse;

/**
 * Created by ace on 2017/8/25.
 */
public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}
