package se.vimatt.webservice.example;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class Main {

    public static void main(String[] args) {
        new UserController(new UserService());
    }
}
