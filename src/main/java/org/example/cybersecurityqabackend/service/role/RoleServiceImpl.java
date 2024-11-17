package org.example.cybersecurityqabackend.service.role;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.entity.Role;
import org.example.cybersecurityqabackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        // Thêm role Admin nếu chưa có
        if (!roleRepository.existsByName("ADMIN")) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDescription("Administrator role with all privileges");
            roleRepository.save(adminRole);
        }

        // Thêm role User nếu chưa có
        if (!roleRepository.existsByName("USER")) {
            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setDescription("Regular user role with basic privileges");
            roleRepository.save(userRole);
        }
    }
}
