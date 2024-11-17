package org.example.cybersecurityqabackend.config;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.entity.Role;
import org.example.cybersecurityqabackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        // Thêm vai trò Admin nếu chưa có
        if (!roleRepository.existsByName("ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDescription("Administrator role with all privileges");
            roleRepository.save(adminRole);
        }

        // Thêm vai trò User nếu chưa có
        if (!roleRepository.existsByName("USER")) {
            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setDescription("Regular user role with basic privileges");
            roleRepository.save(userRole);
        }
    }
}
