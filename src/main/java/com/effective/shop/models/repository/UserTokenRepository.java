package com.effective.shop.models.repository;

import com.effective.shop.models.user.User;
import com.effective.shop.models.user.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {

    void deleteUserTokenByUser(User user);
}
