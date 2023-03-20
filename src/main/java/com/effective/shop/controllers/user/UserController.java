package com.effective.shop.controllers.user;

import com.effective.shop.models.request.LoginRequest;
import com.effective.shop.models.response.LoginResponse;
import com.effective.shop.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    AuthenticateService authenticateService;

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticateService.login(loginRequest));
    }

}
