package se.vimatt.util;

import static spark.Spark.*;

import se.vimatt.webservice.MediaController;
import se.vimatt.webservice.MediaService;

/**
 * Created by victormattsson on 2016-03-23.
 */
public class SparkServer {

    private SparkServer() {}

    public static void start() {
        port(9090);
        staticFileLocation("/web");
        new MediaController(new MediaService());
        awaitInitialization();
    }

	public static void close() {
		stop();
	}
}
