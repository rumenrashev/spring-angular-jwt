package spring.app.auth.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.app.auth.data.entities.AuthorityEntity;
import spring.app.auth.data.entities.AuthorityEnum;
import spring.app.auth.data.entities.UserEntity;
import spring.app.auth.data.repositories.AuthorityRepository;
import spring.app.auth.data.repositories.UserRepository;
import spring.app.auth.exception.AuthorityNotExistsException;
import spring.app.auth.exception.EmailExistsException;
import spring.app.auth.exception.UsernameExistsException;
import spring.app.auth.service.RegisterService;
import spring.app.auth.web.models.RegisterRequest;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterServiceImpl(ModelMapper modelMapper,
                               UserRepository userRepository,
                               AuthorityRepository authorityRepository,
                               PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public String registerUser(RegisterRequest registerRequest) {
        if(this.userRepository.existsByUsername(registerRequest.getUsername())){
            throw new UsernameExistsException();
        }
        if(this.userRepository.existsByEmail(registerRequest.getEmail())){
            throw new EmailExistsException();
        }
        UserEntity userEntity = this.modelMapper.map(registerRequest, UserEntity.class);
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));
        AuthorityEntity userAuthority = this.authorityRepository
                .getByAuthority(AuthorityEnum.USER)
                .orElseThrow(AuthorityNotExistsException::new);
        userEntity.setAuthorities(Set.of(userAuthority));
        UserEntity savedEntity = this.userRepository.save(userEntity);
        return savedEntity.getUsername();
    }
}
