package br.com.api.loginAuth.exceptions;

public class UserNotException extends RuntimeException {

    public UserNotException(String mensagem) {
        super(mensagem);
    }
}
