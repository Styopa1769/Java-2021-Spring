package lesson5.repositories;

import lesson5.model.Account;
import lesson5.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByOwner(User user);
}
