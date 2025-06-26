package br.com.api.loginAuth.domain.dto;

public record RegisterRequestDto(
        String username,

        String email,
        String password)

{

}
