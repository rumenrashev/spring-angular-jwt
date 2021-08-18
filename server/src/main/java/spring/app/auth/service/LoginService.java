package spring.app.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import spring.app.auth.web.models.JwtResponse;
import spring.app.auth.web.models.LoginRequest;

public interface LoginService {

    JwtResponse login(LoginRequest loginRequest, AuthenticationManager authenticationManager);

}
