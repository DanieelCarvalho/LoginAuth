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
import br.com.api.loginAuth.exceptions.InvalidCredentialsException;
import br.com.api.loginAuth.exceptions.InvalidEmailException;
import br.com.api.loginAuth.exceptions.InvalidPasswordException;
import br.com.api.loginAuth.exceptions.InvalidUsernameException;
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

        if (body.username() == null || body.username().length() < 3 || body.username().length() > 30) {
            throw new InvalidUsernameException("O nome deve ter entre 3 e 30 caracteres");
        }
        if (body.email() == null || !body.email().contains("@") || !body.email().contains(".com")) {
            throw new InvalidEmailException("Email inválido");
        }

        if (body.password() == null || body.password().length() < 6) {
            throw new InvalidPasswordException("A senha deve ter no mínimo 6 caracteres");
        }
        boolean hasUppercase = body.password().chars().anyMatch(Character::isUpperCase);
        boolean hasSpecialChar = body.password().matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

        if (!hasUppercase || !hasSpecialChar) {
            throw new InvalidPasswordException(
                    "A senha deve conter pelo menos uma letra maiúscula e um caractere especial");
        }

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setUsername(body.username());
            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseDTO(newUser.getUsername(), token, "Usuário cadastrado com sucesso!"));
        }

        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {

        if (body.email() == null || body.email().trim().isEmpty()) {
            throw new InvalidCredentialsException("O campo de email é obrigatório.");
        }

        if (body.password() == null || body.password().trim().isEmpty()) {
            throw new InvalidCredentialsException("O campo de senha é obrigatório.");
        }
        User user = this.repository.findByEmail(body.email())
                .orElseThrow(() -> new InvalidCredentialsException("Email ou senha incorretos."));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new InvalidCredentialsException("Email ou senha incorretos.");
        }

        String token = this.tokenService.generateToken(user);
        return ResponseEntity
                .ok(new ResponseDTO(user.getUsername(), token, "Autenticação concluída. Acesso liberado!"));

    }

}
