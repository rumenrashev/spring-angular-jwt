package spring.app.auth.service;


import spring.app.auth.web.models.RegisterRequest;

public interface RegisterService {

    String registerUser(RegisterRequest registerRequest);
}
