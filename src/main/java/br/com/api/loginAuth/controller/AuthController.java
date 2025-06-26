package br.com.api.loginAuth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.loginAuth.domain.dto.LoginRequestDTO;
import br.com.api.loginAuth.domain.dto.RegisterRequestDto;
import br.com.api.loginAuth.domain.dto.ResponseDTO;
import br.com.api.loginAuth.repository.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@SecurityRequirement(name = "bearerAuth")
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Cria uma nova conta de usuário no sistema")
    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDto body) {

        return this.service.register(body);
    }

    @Operation(summary = "Autenticar usuário", description = "Realiza login e retorna token JWT")
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {

        return this.service.login(body);
    }

}
