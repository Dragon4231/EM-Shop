package com.effective.shop.controllers.user;

import com.effective.shop.models.request.LoginRequest;
import com.effective.shop.models.request.RegistrationRequest;
import com.effective.shop.models.response.AuthenticateResponse;
import com.effective.shop.models.response.LoginResponse;
import com.effective.shop.service.AuthenticateService;
import com.effective.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    UserService userService;

    @PostMapping("signIn")
    public ResponseEntity<AuthenticateResponse> signIn(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticateService.login(loginRequest));
    }

    @PostMapping("signUp")
    public ResponseEntity<AuthenticateResponse> signUp(@RequestBody RegistrationRequest registrationRequest){
        return userService.createUser(registrationRequest);
    }

}
