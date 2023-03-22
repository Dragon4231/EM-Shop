package com.effective.shop.service;

import com.effective.shop.models.repository.UserRepository;
import com.effective.shop.models.request.RegistrationRequest;
import com.effective.shop.models.response.AuthenticateResponse;
import com.effective.shop.models.user.User;
import com.effective.shop.models.user.UserRole;
import com.effective.shop.security.models.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Set;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    UserRoleService userRoleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleService = userRoleService;
    }

    @Transactional
    public ResponseEntity<AuthenticateResponse> createUser(RegistrationRequest registrationRequest){
        if(Objects.isNull(registrationRequest.getEmail()) ||
                Objects.isNull(registrationRequest.getUsername()) ||
                Objects.isNull(registrationRequest.getPassword())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AuthenticateResponse.builder()
                            .code(400)
                            .result("Any field is null.")
                            .build());
        }

        if(userRepository.existsByUsername(registrationRequest.getUsername())){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AuthenticateResponse.builder()
                            .code(400)
                            .result("User already exists.")
                            .build());
        }


        userRepository.save(User.builder()
                        .balance(0.0)
                        .roles(Set.of(userRoleService.findRoleClient()))
                        .email(registrationRequest.getEmail())
                        .password(passwordEncoder.encode(registrationRequest.getPassword()))
                        .username(registrationRequest.getUsername())
                .build());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthenticateResponse.builder()
                        .code(201)
                        .result("User successfully created.")
                        .build());
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public User getAuthenticated(){
        UserDetails userDetails = (UserDetailsImpl) getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(userDetails.getUsername()).orElse(null);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
