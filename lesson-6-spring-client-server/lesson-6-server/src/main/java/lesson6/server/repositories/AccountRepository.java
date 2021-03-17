package lesson6.server.repositories;

import lesson6.server.model.Account;
import lesson6.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwner(User user);
}
