package se.vimatt.webservice.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class UserService {
    List<User> users = new ArrayList<>();

    public UserService() {

        User user1 = new User();
        user1.setEmail("john@foobar.com");
        user1.setName("john");
        user1.setId("1866d959-4a52-4409-afc8-4f09896f38b2");
        users.add(user1);

        User user2 = new User();
        user2.setId("90d965ad-5bdf-455d-9808-c38b72a5181a");
        user2.setName("anna");
        user2.setEmail("anna@foobar.com");
        users.add(user2);
    }

    public List<User> getAllUsers() {
        return users;
    }

    // returns a single user by id
    public User getUser(String id) {
        User thisUser = null;
        for (User user : users) {
            if (user.getId().equals(id)){
                thisUser =  user;
            }
        }
        return thisUser;
    }

    // creates a new user
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }

    // updates an existing user
    public User updateUser(String id, String name, String email) {
        User user = getUser(id);
        user.setName(name);
        user.setEmail(email);
        return user;
    }

}
