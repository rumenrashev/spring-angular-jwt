package spring.app.auth.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.app.auth.data.repositories.UserRepository;
import spring.app.auth.service.models.UserDetailsServiceModel;

import static spring.app.auth.exception.ExceptionMessages.USERNAME_NOT_FOUND_EXCEPTION_MESSAGE;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDetailsServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetailsServiceModel loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.getByUsername(username)
                .map(entity -> this.modelMapper.map(entity, UserDetailsServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE));
    }


}
