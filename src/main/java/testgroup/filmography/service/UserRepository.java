package testgroup.filmography.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testgroup.filmography.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByName(String name);
    User findByName(String name);
}