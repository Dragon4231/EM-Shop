package com.effective.shop.controllers.admin;

import com.effective.shop.models.request.TopUpBalanceRequest;
import com.effective.shop.models.user.Product;
import com.effective.shop.models.user.User;
import com.effective.shop.service.AdminService;
import com.effective.shop.service.ProductService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/v1/user")
public class AdminUserController {
    AdminService adminService;

    public AdminUserController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize(
            "hasAuthority(T(com.effective.shop.models.user.EUserRole).ADMIN)")
    @ApiOperation(value = "Получение информации о пользователе")
    @ApiImplicitParam(name = "Authorization-Client", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "{token}")
    @PostMapping("getUserByUsername/{username}")
    public User getUserByUsername(@PathVariable String username){
        return adminService.getUserByUsername(username);
    }


    @PreAuthorize(
            "hasAuthority(T(com.effective.shop.models.user.EUserRole).ADMIN)")
    @ApiOperation(value = "Пополнить баланс")
    @ApiImplicitParam(name = "Authorization-Client", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "{token}")
    @PostMapping("topUpBalance")
    public void topUpBalance(@RequestBody TopUpBalanceRequest topUpBalanceRequest){
        adminService.topUpBalance(topUpBalanceRequest);
    }
}
