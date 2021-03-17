package lesson6.server;

import lesson6.server.model.Account;
import lesson6.server.model.User;
import lesson6.server.repositories.AccountRepository;
import lesson6.server.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class UserRepoTest {
    private static final User USER_TO_TEST = new User(1L, "name", "hash", new ArrayList<>());
    private static final Account ACCOUNT_TO_TEST = new Account(1L, "1", USER_TO_TEST);

    @Autowired
    UserRepository userRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    @DisplayName("Тест сохранения сущностей")
    @Order(0)
    void test_save(){
        Assertions.assertEquals(USER_TO_TEST.getName(), userRepository.save(USER_TO_TEST).getName());
        Account savedAccount = accountRepository.save(ACCOUNT_TO_TEST);
        Assertions.assertEquals(ACCOUNT_TO_TEST.getNumber(), savedAccount.getNumber());

        USER_TO_TEST.setAccounts(Collections.singletonList(savedAccount));
        User updatedUser = userRepository.save(USER_TO_TEST);
        // это не будет работать, т.к. по умолчанию ленивая инициализация
        //Assertions.assertEquals(1, updatedUser.getAccounts().size());
        Assertions.assertEquals(1, accountRepository.findAllByOwner(updatedUser).size());
        Assertions.assertEquals(ACCOUNT_TO_TEST.getNumber(), accountRepository.findAllByOwner(updatedUser).get(0).getNumber());
    }

    @Test
    @DisplayName("Тест поиска сущностей")
    @Order(1)
    void test_find(){
        List<User> users = userRepository.findAll();
        List<Account> accounts = accountRepository.findAll();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals(1, accounts.size());
        Assertions.assertEquals(1, accountRepository.findAllByOwner(users.get(0)).size());
    }
}
