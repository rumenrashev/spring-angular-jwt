package spring.app.auth.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.app.auth.data.entities.AuthorityEntity;
import spring.app.auth.data.entities.AuthorityEnum;

import java.util.Optional;


@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity,String> {

    Optional<AuthorityEntity> getByAuthority(AuthorityEnum authority);

    boolean existsByAuthority(AuthorityEnum authority);

}
