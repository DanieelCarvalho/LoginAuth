package br.com.api.loginAuth.exceptions;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException(String mensagem) {
        super(mensagem);
    }
}
