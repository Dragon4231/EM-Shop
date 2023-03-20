package com.effective.shop.service;

import com.effective.shop.models.repository.UserRepository;
import com.effective.shop.models.request.LoginRequest;
import com.effective.shop.models.response.LoginResponse;
import com.effective.shop.models.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public AuthenticateService(AuthenticationManager authenticationManager,
                               PasswordEncoder passwordEncoder,
                               UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public LoginResponse login(LoginRequest request) {
        System.out.println(passwordEncoder.encode(request.getPassword()));
        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            if(passwordEncoder.matches(request.getPassword(), user.getPassword())){
                System.out.println("true");
            }
        });
        return null;
       /* Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), passwordEncoder.encode(request.getPassword())));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = "";*/
    }
}
