package org.example.cybersecurityqabackend.config;

import org.example.cybersecurityqabackend.entity.Role;
import org.example.cybersecurityqabackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataInitializer(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (!roleRepository.existsByName("Admin")) {
            Role adminRole = new Role();
            adminRole.setName("Admin");
            adminRole.setDescription("Administrator role with all privileges");
            roleRepository.save(adminRole);
        }

        // Thêm vai trò USER nếu chưa có
        if (!roleRepository.existsByName("User")) {
            Role userRole = new Role();
            userRole.setName("User");
            userRole.setDescription("Regular user role with basic privileges");
            roleRepository.save(userRole);
        }
    }
}
