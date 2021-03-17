package lesson6.server.repositories;

import lesson6.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //@Query("SELECT u FROM user_entity u WHERE u.status = :status and u.name = :name")
    //User findUserByName(@Param("name") String name);

    //List<User> findAll(Sort sort);
}
