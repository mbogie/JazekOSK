package jazekOSK.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import jazekOSK.entity.User;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByLogin(String login);

}
