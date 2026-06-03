package com.nicolasfernandez.logisticstrackingapi.config;

import com.nicolasfernandez.logisticstrackingapi.entity.Role;
import com.nicolasfernandez.logisticstrackingapi.entity.User;
import com.nicolasfernandez.logisticstrackingapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // 👈 SOLO corre en desarrollo (IMPORTANTE)
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (!userRepository.existsByUsername("admin")) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setRole(Role.ADMIN);
            userRepository.save(user);
            System.out.println("Admin user created");
        } else {
            System.out.println("Admin user already exists");
        }
    }
}