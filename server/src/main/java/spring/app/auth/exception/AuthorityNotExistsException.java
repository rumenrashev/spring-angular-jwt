package spring.app.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static spring.app.auth.exception.ExceptionMessages.AUTHORITY_NOT_EXISTS_MESSAGE;


@ResponseStatus(code = HttpStatus.CONFLICT,reason = AUTHORITY_NOT_EXISTS_MESSAGE)
public class AuthorityNotExistsException extends RuntimeException {

}
