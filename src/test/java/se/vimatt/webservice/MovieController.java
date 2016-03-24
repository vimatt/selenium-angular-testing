package se.vimatt.webservice;

import se.vimatt.util.JsonUtil;

import static spark.Spark.*;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class MovieController {

    public MovieController(final MovieService service) {

        get("/favorites", (request, response) -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return service.getAllMovies();
        }, JsonUtil.json());

        after((req, res) -> res.type("application/json"));

    }
}
