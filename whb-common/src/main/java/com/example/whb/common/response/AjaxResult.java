package com.example.whb.common.response;

import lombok.Data;

@Data
public class AjaxResult<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 消息提示
     */
    private String msg;

    /**
     * 响应的数据
     */
    private T data;

    /**
     * 创建一个表示操作成功的Ajax响应。
     *
     * @param <T>  响应数据的类型，支持泛型，以便适应不同类型的返回数据。
     * @param data 操作成功后返回的具体数据。
     * @return 包含成功标识、成功消息和操作数据的AjaxResult对象。
     */
    public static <T> AjaxResult<T> success(T data) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMsg("操作成功!");
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult<T> success(String msg) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    public static <T> AjaxResult<T> success() {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMsg("操作成功!");
        result.setData(null);
        return result;
    }

    public static <T> AjaxResult<T> success(String msg, T data) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult<T> success(Integer code, String msg, T data) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult<T> error(String msg) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(500);
        result.setMsg(msg);
        return result;
    }

    public static <T> AjaxResult<T> error() {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(500);
        result.setMsg("操作失败!");
        return result;
    }

    public static <T> AjaxResult<T> toAjax(boolean condition) {
        return condition ? success() : error();
    }


    /**
     * 根据条件判断返回成功或失败的Ajax结果。
     *
     * @param condition 条件判断标志，true表示成功，false表示失败。
     * @param msg 成功或失败时返回的信息。
     * @param <T> AjaxResult中数据类型的泛型参数。
     * @return 如果condition为true，返回带有成功信息的AjaxResult；如果condition为false，返回带有失败信息的AjaxResult。
     */
    public static <T> AjaxResult<T> toAjax(boolean condition, String msg) {
        return condition ? success(msg) : error(msg);
    }

    public static <T> AjaxResult<T> error(String msg, T data) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> AjaxResult<T> error(Integer code, String msg) {
        AjaxResult<T> result = new AjaxResult<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
