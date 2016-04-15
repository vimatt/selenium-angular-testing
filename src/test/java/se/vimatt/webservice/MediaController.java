package se.vimatt.webservice;

import se.vimatt.util.JsonUtil;

import static spark.Spark.*;

/**
 * Controller class for the Spark server to handle the requests for the webservice
 * <p>
 * Created by victormattsson on 2016-03-22.
 */
public class MediaController {

    public MediaController(final MediaService service) {

        //Tells spark that when a get request is done to '/favoritemovies' it shall return all movies from the service
        get("/favoritemovies", (request, response) -> {
            //Making a thread sleep on every request for the purpose of showing
            //that the function to wait for angular works as intended
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }
            return service.getAllMovies();
            //Calls the JsonUtil class to make our response return in Json format
        }, JsonUtil.json());

        //Tells spark that when a get request is done to '/favoriteseries' it shall return all series from the service
        get("/favoriteseries", (request, response) -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw e;
            }
            return service.getAllSeries();
        }, JsonUtil.json());

        //A filter that changes the content type of every response to 'application/json'
        after((req, res) -> res.type("application/json"));

    }
}
