package org.example.cybersecurityqabackend.service.user;

import org.example.cybersecurityqabackend.entity.User;
import org.example.cybersecurityqabackend.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSerivce{


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}