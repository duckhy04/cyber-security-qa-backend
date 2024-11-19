package org.example.cybersecurityqabackend.service.auth;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.LoginRequest;
import org.example.cybersecurityqabackend.dto.RegisterRequest;
import org.example.cybersecurityqabackend.dto.RegisterResponse;
import org.example.cybersecurityqabackend.entity.Role;
import org.example.cybersecurityqabackend.entity.User;
import org.example.cybersecurityqabackend.exception.UsernameAlreadyExistsException;
import org.example.cybersecurityqabackend.repository.RoleRepository;
import org.example.cybersecurityqabackend.repository.UserRepository;
import org.example.cybersecurityqabackend.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public String login(LoginRequest loginDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenUtil.generateToken(authentication);
    }

    @Override
    public RegisterResponse register(RegisterRequest registerDto) {

        Optional<User> user = userRepository.findByUsernameOrEmail(registerDto.getUsername(), registerDto.getEmail());

        if (user.isPresent()) {
            throw new UsernameAlreadyExistsException("Username or email already exists");
        }

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);

        User userEntity = new User();
        userEntity.setUsername(registerDto.getUsername());
        userEntity.setEmail(registerDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userEntity.setRoles(roles);
        userRepository.save(userEntity);

        return new RegisterResponse("Registered successfully", "success");
    }
}
