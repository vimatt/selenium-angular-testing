package se.vimatt.util;

import static spark.Spark.*;

import se.vimatt.webservice.MediaController;
import se.vimatt.webservice.MediaService;

/**
 * Class that handles the Spark server, which when started hosts the Demo Site
 * on localhost and also starts the small webservice which the demo site
 *
 * Created by victormattsson on 2016-03-23.
 */
public class SparkServer {

    private SparkServer() {}

    public static void start() {
        //Sets the localhost port
        port(9090);
        //Tells Spark where to locate the web application
        staticFileLocation("/web");
        //Instantiates the Media Contorller service
        new MediaController(new MediaService());
        awaitInitialization();
    }

    //Stops the Spark server
	public static void close() {
		stop();
	}
}
