package com.example.whb.controller;

import com.example.whb.common.controller.BaseController;
import com.example.whb.common.response.AjaxResult;
import com.example.whb.domain.LoginUser;
import com.example.whb.domain.vo.LoginBody;
import com.example.whb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController extends BaseController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/auth/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        String token = loginService.login(loginBody);
        return success(token);
    }
}
