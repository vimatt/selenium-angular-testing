package se.vimatt.webservice;


import se.vimatt.util.SparkServer;

/**
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
