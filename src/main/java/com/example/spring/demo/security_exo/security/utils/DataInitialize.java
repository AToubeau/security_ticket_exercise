package com.example.spring.demo.security_exo.security.utils;

import com.example.spring.demo.security_exo.dal.domain.Role;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.RoleUser;
import com.example.spring.demo.security_exo.dal.repository.RoleRepository;
import com.example.spring.demo.security_exo.dal.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitialize implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final String mySuperPassword = "mySuperPassword";

    public DataInitialize(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role roleUser = new Role(RoleUser.ROLE_USER.toString());
            Role roleAdmin = new Role(RoleUser.ROLE_ADMIN.toString());
            Role roleTechnicien = new Role(RoleUser.ROLE_TECHNICIEN.toString());
            Set<Role> rolesUser = Set.of(roleUser);
            Set<Role> rolesAdmin = Set.of(roleUser, roleAdmin);
            Set<Role> rolesTechnicien = Set.of(roleUser, roleTechnicien);

            User user = new User("simple User", "simple@user.be", passwordEncoder.encode(mySuperPassword), rolesUser);
            User userAdmin = new User("admin User", "super@admin.be", passwordEncoder.encode(mySuperPassword), rolesAdmin);
            User userTechnicien = new User("techGuy", "guy@tech.be", passwordEncoder.encode(mySuperPassword), rolesTechnicien);

            roleRepository.saveAll(List.of(roleUser, roleAdmin, roleTechnicien));
            userRepository.saveAll(List.of(user, userAdmin, userTechnicien));
        }
    }
}
