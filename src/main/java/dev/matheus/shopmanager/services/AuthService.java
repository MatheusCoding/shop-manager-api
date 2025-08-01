package dev.matheus.shopmanager.services;


import dev.matheus.shopmanager.config.JwtUtils;
import dev.matheus.shopmanager.dto.AuthResponse;
import dev.matheus.shopmanager.dto.LoginRequest;
import dev.matheus.shopmanager.models.User;
import dev.matheus.shopmanager.models.UserRole;
import dev.matheus.shopmanager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("Senha inválida");
        }

        return new AuthResponse(jwtUtils.generateToken(user.getUsername()));
    }

    public User createUser(String username, String password, UserRole role) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username já existe");
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(role);

        return userRepository.save(newUser);
    }
}