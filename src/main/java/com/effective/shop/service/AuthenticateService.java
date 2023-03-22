package com.effective.shop.service;

import com.effective.shop.models.repository.UserRepository;
import com.effective.shop.models.repository.UserTokenRepository;
import com.effective.shop.models.request.LoginRequest;
import com.effective.shop.models.response.AuthenticateResponse;
import com.effective.shop.models.response.LoginResponse;
import com.effective.shop.models.user.User;
import com.effective.shop.models.user.UserToken;
import com.effective.shop.security.models.UserDetailsImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.util.Optional;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parserBuilder;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;
import static java.time.LocalDateTime.now;
import static java.time.ZoneOffset.UTC;
import static java.util.Date.from;

@Service
@Slf4j
public class AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final UserTokenRepository userTokenRepository;


    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expirationMs}")
    private long expirationMs;
    private JwtBuilder tokenBuilder;
    private JwtParser tokenParser;

    public AuthenticateService(AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserService userService, UserTokenRepository userTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.userTokenRepository = userTokenRepository;
    }

    @PostConstruct
    private void init() {
        Key key = hmacShaKeyFor(secret.getBytes());
        tokenBuilder = builder().signWith(key);
        tokenParser = parserBuilder().setSigningKey(key).build();
    }

    @Transactional
    public AuthenticateResponse login(LoginRequest request) {
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String token = createToken(userDetails);

        return AuthenticateResponse
                .builder()
                .code(200)
                .result("token: "+token)
                .build();
    }

    @Transactional
    public String createToken(UserDetailsImpl user){
        Instant created = now().toInstant(UTC);
        Instant expired = created.plusMillis(expirationMs);

        String token = tokenBuilder
                .setIssuedAt(from(created))
                .setSubject(user.getUsername())
                .setExpiration(from(expired))
                .compact();

        User userWithToken = userService.findUserByUsername(user.getUsername());

        userTokenRepository.deleteUserTokenByUser(userWithToken);

        userTokenRepository.save(UserToken.builder()
                        .token(token)
                        .user(userWithToken)
                .build());

        return token;
    }

}
