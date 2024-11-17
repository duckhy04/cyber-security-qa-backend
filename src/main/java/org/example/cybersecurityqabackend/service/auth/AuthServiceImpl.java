package org.example.cybersecurityqabackend.service.auth;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.LoginDto;
import org.example.cybersecurityqabackend.dto.RegisterDto;
import org.example.cybersecurityqabackend.entity.Role;
import org.example.cybersecurityqabackend.entity.User;
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

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
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
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtTokenUtil.generateToken(authentication);
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())){
            throw new RuntimeException("Username is already in use");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }

        String encodePassword = passwordEncoder.encode(registerDto.getPassword());

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("User").orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(encodePassword);
        user.setRoles(roles);
        userRepository.save(user);

        return "Registered successfully";
    }


}
