package se.vimatt.webservice;


import se.vimatt.util.SparkServer;

/**
 * Class to manually start the spark server and webservice
 * <p>
 * Created by victor mattsson on 2016-03-22.
 */
public class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SparkServer.start();
        new MediaController(new MediaService());
    }
}
