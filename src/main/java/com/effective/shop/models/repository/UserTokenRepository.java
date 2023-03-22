package com.effective.shop.models.repository;

import com.effective.shop.models.user.User;
import com.effective.shop.models.user.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken, Long> {
    @Query("select t from UserToken t where t.token = :token and t.user.username = :username ")
    Optional<UserToken> findByUsernameAndToken(@Param("token") String token,
                        @Param("username") String username);
    void deleteUserTokenByUser(User user);
}
