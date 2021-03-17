package lesson6.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lesson6.server.model.Account;
import lesson6.server.repositories.AccountRepository;
import lesson6.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("add, get account")
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts/{id}")
    @ApiOperation("get accounts by user id")
    public ResponseEntity<List<Account>> getUserAccounts(@PathVariable("id") Long id) {
        List<Account> result = accountRepository.findAllByOwner(userRepository.getOne(id));

        if (!result.isEmpty())
            return ResponseEntity.ok(accountRepository.findAllByOwner(userRepository.getOne(id)));

        return ResponseEntity.notFound().build();
    }
}
