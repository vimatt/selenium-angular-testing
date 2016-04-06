package se.vimatt.util;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class ResponseError {

    private final String message;

    public ResponseError(String message, String... args) {
        this.message = String.format(message, args);
    }

    public ResponseError(Exception e) {
        this.message = e.getMessage();
    }

    public String getMessage() {
        return this.message;
    }
}

