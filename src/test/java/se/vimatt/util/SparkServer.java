package se.vimatt.util;

import static spark.Spark.*;

import se.vimatt.webservice.MovieController;
import se.vimatt.webservice.MovieService;

/**
 * Created by victormattsson on 2016-03-23.
 */
public class SparkServer {


    public static void start() {
        port(9090);
        staticFileLocation("/web");
        new MovieController(new MovieService());
        awaitInitialization();
//        get("/hello", (request, response) -> "asdf");
    }

	public static void close() {
		stop();
	}
}
