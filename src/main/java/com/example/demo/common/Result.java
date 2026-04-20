package com.example.demo.common;

import lombok.Data;

/**
 * 统一响应结果类
 */
@Data
public class Result<T> {
    private Integer code; // 响应码
    private String msg;   // 响应信息
    private T data;      // 响应数据

    /**
     * 成功响应
     * @param data 响应数据
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(1);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    /**
     * 失败响应
     * @param msg 响应信息
     * @return Result对象
     */
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<>();
        result.setCode(0);
        result.setMsg(msg);
        return result;
    }
} 