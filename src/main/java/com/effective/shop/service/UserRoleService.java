package com.effective.shop.service;

import com.effective.shop.models.repository.UserRoleRepository;
import com.effective.shop.models.user.EUserRole;
import com.effective.shop.models.user.UserRole;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRole findRoleClient(){
        return userRoleRepository.findByTitle(EUserRole.CLIENT).orElse(null);
    }
}
