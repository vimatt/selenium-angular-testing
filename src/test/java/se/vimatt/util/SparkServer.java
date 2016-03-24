package se.vimatt.util;

import static spark.Spark.*;

/**
 * Created by victormattsson on 2016-03-23.
 */
public class SparkServer {


    public static void start() {
        port(9090);
        staticFileLocation("/web");
//        get("/hello", (request, response) -> "asdf");
    }
}
