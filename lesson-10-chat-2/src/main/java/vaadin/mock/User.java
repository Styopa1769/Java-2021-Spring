package vaadin.mock;

import java.util.Map;

public class User {
    public static final Map<String, User> USERS_BY_NAME = Map.of(
            "Mr. Smith", new User("Mr. Smith", "http://simpleicon.com/wp-content/uploads/user1.png"),
            "Styopa1769", new User("Styopa1769", "https://mir-s3-cdn-cf.behance.net/project_modules/disp/ce54bf11889067.562541ef7cde4.png"));

    public static final User GUEST = new User("Guest", "https://image.flaticon.com/icons/png/512/2532/2532767.png");

    private final String name;
    private final String picture;

    public User(String name, String picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }
}
