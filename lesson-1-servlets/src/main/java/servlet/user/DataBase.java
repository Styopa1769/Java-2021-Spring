package servlet.user;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    public final static DataBase INSTANCE = new DataBase();

    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public DataBase() {
        String login = "shcstepan";
        userMap.put(login, new User("Stepan","Shcherbakov", login, "1769"));
    }

    public User getUserByLogin(String login){
        return userMap.get(login);
    }

    public void putUser(User user){
        userMap.put(user.getLogin(), user);
    }

    public boolean login(String login, String password){
        return userMap.containsKey(login) && BCrypt.checkpw(password, userMap.get(login).getPassword());
    }
}
