package spring.app.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static spring.app.auth.exception.ExceptionMessages.USERNAME_EXISTS_MESSAGE;


@ResponseStatus(code = HttpStatus.CONFLICT)
public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException() {
        super(USERNAME_EXISTS_MESSAGE);
    }
}
