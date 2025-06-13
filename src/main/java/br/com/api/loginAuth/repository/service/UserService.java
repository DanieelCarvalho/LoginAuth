package br.com.api.loginAuth.repository.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.api.loginAuth.domain.dto.LoginRequestDTO;
import br.com.api.loginAuth.domain.dto.RegisterRequestDto;
import br.com.api.loginAuth.domain.dto.ResponseDTO;
import br.com.api.loginAuth.domain.model.User;
import br.com.api.loginAuth.infra.security.TokenService;
import br.com.api.loginAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestDto body) {
        Optional<User> user = this.repository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setUsername(body.username());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity login(@RequestBody LoginRequestDTO body) {

        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getUsername(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
