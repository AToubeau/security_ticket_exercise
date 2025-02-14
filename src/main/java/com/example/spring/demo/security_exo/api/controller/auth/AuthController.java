package com.example.spring.demo.security_exo.api.controller.auth;

import com.example.spring.demo.security_exo.api.model.login.LoginRequestBody;
import com.example.spring.demo.security_exo.api.model.login.LoginResponseBody;
import com.example.spring.demo.security_exo.api.model.user.CreateUserRequestBody;
import com.example.spring.demo.security_exo.api.model.user.CreateUserResponseBody;
import com.example.spring.demo.security_exo.bll.service.UserService;
import com.example.spring.demo.security_exo.dal.domain.User;
import com.example.spring.demo.security_exo.security.utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseBody> login(@RequestBody LoginRequestBody loginForm) {
        User user = userService.login(loginForm.getEmail(), loginForm.getPassword());
        LoginResponseBody dto = LoginResponseBody.fromEntity(user);
        String token = jwtUtil.generateToken(user);
        dto.setToken(token);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/register")
    public ResponseEntity<CreateUserResponseBody> register(@RequestBody CreateUserRequestBody createUserForm) {
        User user = userService.CreateUser(createUserForm);
        CreateUserResponseBody dto = CreateUserResponseBody.fromEntity(user);
        String token = jwtUtil.generateToken(user);
        dto.setToken(token);

        return ResponseEntity.ok(dto);
    }
}
