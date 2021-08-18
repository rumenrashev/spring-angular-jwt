package spring.app.auth.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import spring.app.auth.service.LoginService;
import spring.app.auth.service.models.GrantedAuthorityServiceModel;
import spring.app.auth.service.models.UserDetailsServiceModel;
import spring.app.auth.utils.JwtUtils;
import spring.app.auth.web.models.JwtResponse;
import spring.app.auth.web.models.LoginRequest;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    public LoginServiceImpl(ModelMapper modelMapper, JwtUtils jwtUtils) {
        this.modelMapper = modelMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest, AuthenticationManager authenticationManager) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsServiceModel userDetails = (UserDetailsServiceModel) authentication.getPrincipal();
        String token = jwtUtils.generateJwtToken(userDetails.getUsername());
        JwtResponse jwt = this.modelMapper.map(userDetails, JwtResponse.class);
        Set<String> roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthorityServiceModel::getAuthority)
                .collect(Collectors.toSet());
        return jwt
                .setToken(token)
                .setRoles(roles);
    }
}
