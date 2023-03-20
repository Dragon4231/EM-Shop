package com.effective.shop.models.repository;


import com.effective.shop.models.user.EUserRole;
import com.effective.shop.models.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findAll();

    Optional<UserRole> findByTitle(EUserRole role);
}
