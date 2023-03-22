package com.effective.shop.controllers.user;

import com.effective.shop.models.request.LoginRequest;
import com.effective.shop.models.request.RegistrationRequest;
import com.effective.shop.models.response.AuthenticateResponse;
import com.effective.shop.models.response.LoginResponse;
import com.effective.shop.security.models.UserDetailsImpl;
import com.effective.shop.service.AuthenticateService;
import com.effective.shop.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    UserService userService;

    @ApiOperation(value = "Вход и получение токена")
    @PostMapping("signIn")
    public ResponseEntity<AuthenticateResponse> signIn(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authenticateService.login(loginRequest));
    }

    @ApiOperation(value = "Регистрация")
    @PostMapping("signUp")
    public ResponseEntity<AuthenticateResponse> signUp(@RequestBody RegistrationRequest registrationRequest){
        return userService.createUser(registrationRequest);
    }

}
