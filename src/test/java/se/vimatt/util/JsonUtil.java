package se.vimatt.util;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class JsonUtil {

    private JsonUtil() {}

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static ResponseTransformer json() {
        return JsonUtil::toJson;
    }
}
