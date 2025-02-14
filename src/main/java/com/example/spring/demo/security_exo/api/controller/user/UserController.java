package com.example.spring.demo.security_exo.api.controller.user;

import com.example.spring.demo.security_exo.api.model.user.UserDetailResponseBody;
import com.example.spring.demo.security_exo.bll.service.UserService;
import com.example.spring.demo.security_exo.dal.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailResponseBody> getMe(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        UserDetailResponseBody userDetailResponseBody = UserDetailResponseBody.fromEntity(user);

        return ResponseEntity.ok(userDetailResponseBody);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDetailResponseBody>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users.stream()
                .map(UserDetailResponseBody::fromEntity)
                .toList());
    }
}
