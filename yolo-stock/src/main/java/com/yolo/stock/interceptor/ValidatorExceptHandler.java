package com.yolo.stock.interceptor;


import com.yolo.stock.common.constant.CommonConstants;
import com.yolo.stock.common.msg.ObjectRestResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
    * 功能描述 拦截器--实体类校验
    * @author YOLO
    * @date 2020-10-09
    * @param
    * @return
*/
@ControllerAdvice
@Component
public class ValidatorExceptHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ObjectRestResponse handle(MethodArgumentNotValidException exception){
        String message = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ObjectRestResponse objectRestResponse =new ObjectRestResponse();
        objectRestResponse.setMessage(message);
        objectRestResponse.setStatus(CommonConstants.VALID_ERROR_CODE);
        return objectRestResponse;
    }
}
