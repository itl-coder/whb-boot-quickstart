package com.example.whb.controller.menu;

import com.example.whb.common.response.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MenuController {

    @GetMapping("/menu/{userId}")
    public AjaxResult getMenu(@PathVariable Integer userId) {
        // 根据登录用户id获取用户的菜单
        return AjaxResult.success("菜单");
    }
}
