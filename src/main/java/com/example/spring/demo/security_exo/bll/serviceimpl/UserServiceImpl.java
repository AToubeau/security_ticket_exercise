package com.example.spring.demo.security_exo.bll.serviceimpl;

import com.example.spring.demo.security_exo.api.model.user.CreateUserRequestBody;
import com.example.spring.demo.security_exo.bll.service.UserService;
import com.example.spring.demo.security_exo.dal.domain.Role;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.dal.domain.enums.RoleUser;
import com.example.spring.demo.security_exo.dal.repository.RoleRepository;
import com.example.spring.demo.security_exo.dal.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(
                user.getEmail(),
                user.getEmail(),
                user.getPassword(),
                user.getRoles()
        );
    }

    @Override
    public User login(String email, String password) {
        //todo faire une exception pour ça ?
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Wrong password");
        }
        return user;
    }

    @Override
    public User CreateUser(CreateUserRequestBody request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            //todo implémenté la gestion des exception
            throw new RuntimeException("Email already exists");
        }
        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        Role role = roleRepository.findByName(RoleUser.ROLE_USER.toString())
                .orElseThrow(() -> new RuntimeException("No role found for basic user"));
        user.addRole(role);
        user = userRepository.save(user);

        return user;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
