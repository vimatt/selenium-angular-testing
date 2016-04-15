package se.vimatt.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Class that handles the response format in our web service to Json
 *
 * Created by victormattsson on 2016-03-22.
 */
public class JsonUtil {

    private JsonUtil() {}

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    //Returns a Spark ResponseTransformer to make sure we get our response in json format
    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
