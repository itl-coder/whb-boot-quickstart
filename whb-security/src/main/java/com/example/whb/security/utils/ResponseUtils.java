package com.example.whb.security.utils;


import com.example.whb.common.response.AjaxResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description: 响应工具类
 */
public class ResponseUtils {
    public static void responseJson(HttpServletResponse response, AjaxResult ajaxResult) throws Exception {
        try {
            // 设置响应头
            response.setContentType("text/html; charset=utf-8");
            // 将对象转换为json字符串
            String json = new ObjectMapper().writeValueAsString(ajaxResult);
            // 获取PrintWriter对象
            PrintWriter printWriter = response.getWriter();
            // 将json字符串写入PrintWriter对象
            printWriter.write(json);
            // 刷新PrintWriter对象
            printWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}