package sw.goku.servidor.securiry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw.goku.servidor.securiry.repository.user.Users;
import sw.goku.servidor.securiry.repository.user.Role;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);

    @Query(value = "SELECT u.roles FROM users u WHERE u.username = ?1", nativeQuery = true)
    Role findRoleByUsername(String username);
}
