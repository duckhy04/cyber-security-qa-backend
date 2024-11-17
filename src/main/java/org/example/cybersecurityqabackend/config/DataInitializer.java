package org.example.cybersecurityqabackend.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
    }
}
