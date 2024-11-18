package org.example.cybersecurityqabackend.service.role;

import jakarta.annotation.PostConstruct;
import org.example.cybersecurityqabackend.entity.Role;
import org.example.cybersecurityqabackend.entity.User;
import org.example.cybersecurityqabackend.repository.RoleRepository;
import org.example.cybersecurityqabackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        // Kiểm tra và thêm ROLE_ADMIN nếu chưa có
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        // Kiểm tra và thêm ROLE_USER nếu chưa có
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        // Tạo tài khoản admin nếu chưa tồn tại
        if (userRepository.findByUsernameOrEmail("admin", "admin@gmail.com").isEmpty()) {
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);

            User adminUser = new User();
            adminUser.setName("Admin");
            adminUser.setEmail("admin@gmail.com");
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123"));
            adminUser.setRoles(roles);

            userRepository.save(adminUser);
        }
    }

}
