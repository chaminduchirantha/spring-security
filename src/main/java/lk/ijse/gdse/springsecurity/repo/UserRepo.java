package lk.ijse.gdse.springsecurity.repo;

import lk.ijse.gdse.springsecurity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User , Long> {
    Optional<User> fidByUserName(String username);
}
