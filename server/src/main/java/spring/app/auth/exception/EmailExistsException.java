package spring.app.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static spring.app.auth.exception.ExceptionMessages.EMAIL_EXISTS_MESSAGE;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmailExistsException extends RuntimeException {

    public EmailExistsException() {
        super(EMAIL_EXISTS_MESSAGE);
    }
}
