package br.com.api.loginAuth.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
                @NotBlank(message = "O nome é obrigatório") String username,

                @Email(message = "Email inválido") String email,
                @Size(min = 6, message = "A senha deve ter no minimo 6 caracteres") String password)

{

}
