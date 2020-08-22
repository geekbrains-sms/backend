package mrtrix.geekbrains.smsbackend.repository;

import mrtrix.geekbrains.smsbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
