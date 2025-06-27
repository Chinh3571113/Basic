package com.example.basic.login;

import com.example.basic.request.BlacklistedRequest;
import com.example.basic.request.LoginRequest;
import com.example.basic.response.BaseResponse;
import com.example.basic.services.BlacklistedService;
import com.example.basic.services.JwtService;
import com.example.basic.utils.JwtHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("api/v1/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private BlacklistedService blacklistedService;
    @Autowired
    private JwtService jwtService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        String token = loginService.login(request.getUsername(), request.getPassword());
        BaseResponse response = new BaseResponse();
        response.setData(token);
        response.setMessage("success");
        response.setCode(200);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/blacklisted")
    public ResponseEntity<?> blacklisted(@Validated @RequestBody BlacklistedRequest request) {
        BaseResponse response = blacklistedService.create(request.getToken());
        return ResponseEntity.ok(response);
    }
}
