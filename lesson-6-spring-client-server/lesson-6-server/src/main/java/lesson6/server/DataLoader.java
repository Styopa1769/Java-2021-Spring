package lesson6.server;

import lesson6.server.model.Account;
import lesson6.server.model.ApiUser;
import lesson6.server.model.User;
import lesson6.server.repositories.AccountRepository;
import lesson6.server.repositories.ApiUserRepository;
import lesson6.server.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ApiUserRepository apiUserRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        User user = new User(1L, "name", "hash", new ArrayList<>());
        Account account = new Account(1L, "1", user);

        User savedUser = userRepository.save(user);
        accountRepository.save(account);
        savedUser.setAccounts(Collections.singletonList(account));
        userRepository.save(user);

        apiUserRepository.save(new ApiUser(1L, "styopa", BCrypt.hashpw("hash", BCrypt.gensalt())));
    }
}
