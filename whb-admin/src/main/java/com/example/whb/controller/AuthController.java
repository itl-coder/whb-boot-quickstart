package com.example.whb.controller;

import com.example.whb.common.controller.BaseController;
import com.example.whb.common.response.AjaxResult;
import com.example.whb.domain.vo.LoginBody;
import com.example.whb.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Api(tags = "认证服务")
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {
    @Autowired
    private LoginService loginService;

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        return success("登录成功", loginService.login(loginBody));
    }
}
