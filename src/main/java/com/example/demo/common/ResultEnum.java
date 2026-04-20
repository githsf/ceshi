package com.example.demo.common;

import lombok.Getter;

/**
 * 统一响应状态枚举
 */
@Getter
public enum ResultEnum {
    SUCCESS(1, "操作成功"),
    ERROR(0, "操作失败"),
    SYSTEM_ERROR(-1, "系统异常"),
    DATA_NOT_FOUND(10001, "数据不存在"),
    PARAM_ERROR(10002, "参数错误"),
    USERNAME_EXIST(10003, "用户名已存在"),
    PHONE_EXIST(10004, "手机号已存在");

    private final Integer code;
    private final String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
} 