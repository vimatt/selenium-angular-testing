package se.vimatt.webservice.example;

import se.vimatt.util.JsonUtil;
import se.vimatt.util.ResponseError;

import static spark.Spark.*;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class UserController {
    JsonUtil asd = new JsonUtil();

    public UserController(final UserService service) {

        get("/users", (req, res) -> service.getAllUsers(), JsonUtil.json());

        get("/user/:id", (req, res) -> {
            String id = req.params(":id");
            User user = service.getUser(id);
            if (user != null) {
                return user;
            }
            res.status(400);
            return new ResponseError("No user with id '%s' found", id);
        }, JsonUtil.json());

        after((req, res) -> res.type("application/json"));
    }

}
