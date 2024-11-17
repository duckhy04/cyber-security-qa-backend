package org.example.cybersecurityqabackend.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.JwtAuthResponse;
import org.example.cybersecurityqabackend.dto.LoginDto;
import org.example.cybersecurityqabackend.dto.RegisterDto;
import org.example.cybersecurityqabackend.entity.User;
import org.example.cybersecurityqabackend.repository.UserRepository;
import org.example.cybersecurityqabackend.service.auth.AuthService;
import org.example.cybersecurityqabackend.util.JwtTokenUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/login")
    public void login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws IOException, JSONException {
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);


        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsernameOrEmail());
        Optional<User> optionalUser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), userDetails.getUsername());
        if (optionalUser.isPresent()) {
            response.getWriter().write(new JSONObject()
                    .put("userId", optionalUser.get().getId())
                    .toString()
            );
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, " +
                    "X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        }

//        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }
}

