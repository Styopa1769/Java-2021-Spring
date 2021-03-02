package spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class LoadDatabase implements CommandLineRunner{
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... strings) throws Exception {
        userRepository.save(new User("User1"));
        userRepository.save(new User("User2"));
        userRepository.save(new User("User3"));
        userRepository.save(new User("User4"));
    }
}