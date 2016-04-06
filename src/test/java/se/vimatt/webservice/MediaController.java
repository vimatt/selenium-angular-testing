package se.vimatt.webservice;

import se.vimatt.util.JsonUtil;

import static spark.Spark.*;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class MediaController {

    public MediaController(final MediaService service) {

        get("/favoritemovies", (request, response) -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }
            return service.getAllMovies();
        }, JsonUtil.json());

        get("/favoriteseries", (request, response) -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }
            return service.getAllSeries();
        }, JsonUtil.json());

        after((req, res) -> res.type("application/json"));

    }
}
