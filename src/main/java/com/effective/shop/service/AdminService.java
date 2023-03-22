package com.effective.shop.service;

import com.effective.shop.models.repository.UserRepository;
import com.effective.shop.models.request.TopUpBalanceRequest;
import com.effective.shop.models.user.User;
import com.effective.shop.models.user.UserRole;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Set;

@Service
public class AdminService {
    UserService userService;

    UserRoleService userRoleService;

    PasswordEncoder passwordEncoder;

    public AdminService(UserService userService, UserRoleService userRoleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username){
        return userService.findUserByUsername(username);
    }

    public void topUpBalance(TopUpBalanceRequest topUpBalanceRequest){
        User user = userService.findUserByUsername(topUpBalanceRequest.getUsername());
        user.setBalance(user.getBalance()+topUpBalanceRequest.getAddMoney());
        userService.saveUser(user);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initSuperAdmin(){
        UserRole userRole1 = userRoleService.findRoleAdmin();
        UserRole userRole2 = userRoleService.findRoleClient();
        if(Objects.isNull(userService.findUserByUsername("superadmin"))){
            userService.saveUser(User.builder()
                    .balance(0.0)
                    .username("superadmin")
                    .password(passwordEncoder.encode("superadmin"))
                    .roles(Set.of(userRole1, userRole2))
                    .email("superadmin")
                    .build());
        }
    }
}
