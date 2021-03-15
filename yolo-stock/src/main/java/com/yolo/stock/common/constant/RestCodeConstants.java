package com.yolo.stock.common.constant;

/**
 * Created by ace on 2017/8/23.
 */
public class RestCodeConstants {

    public static final int TOKEN_ERROR_CODE = 40101;
    public static final int TOKEN_FORBIDDEN_CODE = 40301;

    public static final String MESSAGE_SUCCESS = "请求成功";
    public static final String MESSAGE_FAILED = "请求失败";
    /**
    * 请求成功返回码
    **/
    public static final int CODE_SUCCESS = 200;
    /**
     * 请求失败返回码
     **/
    public static final int CODE_FAILED = 500;

    /**
     * 发送短信失败返回码
     **/
    public static final int MESSAGE_SEND_FAILED_CODE = 50001;
    /**
     * 发送短信失败信息
     **/
    public static final String MESSAGE_SEND_FAILED_MESSAGE = "发送消息失败";

    /**
     * 添加短信模板失败返回码
     **/
    public static final int ADD_TEMPLATE_FAILED_CODE = 50002;
    /**
     * 添加短信模板失败信息
     **/
    public static final String ADD_TEMPLATE_FAILED_MESSAGE = "创建模板失败";

    /**
     * 修改短信模板失败返回码
     **/
    public static final int MODIFY_TEMPLATE_FAILED_CODE = 50003;

    /**
     * 不可修改短信模板提示信息
     **/
    public static final String MODIFY_TEMPLATE_FAILED_MESSAGE = "审核中或者审核通过的模板不可修改";

    /**
     * 删除短信模板失败返回码
     **/
    public static final int DELETE_TEMPLATE_FAILED_CODE = 50004;

    /**
     * 不可修改短信模板提示信息
     **/
    public static final String DELETE_TEMPLATE_FAILED_MESSAGE = "模板正在审核中，不允许删除";

}
