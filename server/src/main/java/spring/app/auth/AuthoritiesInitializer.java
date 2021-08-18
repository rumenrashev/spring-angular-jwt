package spring.app.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.app.auth.data.entities.AuthorityEntity;
import spring.app.auth.data.entities.AuthorityEnum;
import spring.app.auth.data.repositories.AuthorityRepository;

import javax.transaction.Transactional;
import java.util.EnumSet;

@Component
public class AuthoritiesInitializer implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    public AuthoritiesInitializer(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        EnumSet.allOf(AuthorityEnum.class)
                .stream()
                .filter(a-> !this.authorityRepository.existsByAuthority(a))
                .map(a-> new AuthorityEntity().setAuthority(a))
                .forEach(this.authorityRepository::save);
    }
}
