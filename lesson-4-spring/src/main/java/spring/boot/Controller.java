package spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
public class Controller {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/welcome", method= RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> welcome(){
        return new ResponseEntity<String>("Hello World!", HttpStatus.OK);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> welcomeUsers(@RequestParam(value = "name", defaultValue = "World", required = false) String name){
        return new ResponseEntity<String>(String.format("Hello, %s!", name), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method=RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<User>(userRepository.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userRepository.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
