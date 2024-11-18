package org.example.cybersecurityqabackend.service.user;

import org.example.cybersecurityqabackend.entity.User;

public interface UserSerivce {
    User findByUsername(String username, String email);
}
