package se.vimatt.webservice;


import se.vimatt.util.SparkServer;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class Main {

    public static void main(String[] args) {
        SparkServer.start();
        new MovieController(new MovieService());
    }
}
