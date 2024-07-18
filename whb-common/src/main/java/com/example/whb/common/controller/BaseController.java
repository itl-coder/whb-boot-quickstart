package com.example.whb.common.controller;


import com.example.whb.common.response.AjaxResult;
import com.example.whb.common.utils.DateUtils;
import com.example.whb.common.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * web层通用数据处理
 */
@Slf4j
public class BaseController {
    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                log.info("日期数据获取自动转换: {}, {}", text, DateUtils.parseDate(text));
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtils.startPage();
    }


    /**
     * 清理分页的线程变量
     */
    protected void clearPage() {
        PageUtils.clearPage();
    }


    /**
     * 返回成功
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }


    /**
     * 返回成功
     */
    public AjaxResult success(String msg, Object data) {
        return AjaxResult.success(msg, data);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }

    /**
     * 返回成功消息
     */
    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }

    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    protected AjaxResult toAjax(int rows, String successMsg, String errorMsg) {
        return rows > 0 ? AjaxResult.success(successMsg) : AjaxResult.error(errorMsg);
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }

    protected AjaxResult toAjax(boolean result, String successMsg, String errorMsg) {
        return result ? success(successMsg) : error(errorMsg);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return "redirect:" + url;
    }
}
