package WebLab4.repository;

import WebLab4.entity.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserDB, String> {
    UserDB findByLogin(String login);
}
