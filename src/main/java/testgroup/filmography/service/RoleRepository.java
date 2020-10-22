package testgroup.filmography.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import testgroup.filmography.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
