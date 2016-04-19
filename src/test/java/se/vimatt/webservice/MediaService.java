package se.vimatt.webservice;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor mattsson on 2016-03-22.
 */
public class MediaService {

    private final Logger logger = LoggerFactory.getLogger(MediaService.class);
    private static final String MEDIA_FILE = "src/test/resources/media.json";

    public List<Media> getAllMovies() {
        return getJsonDataAsList("movies");
    }

    public List<Media> getAllSeries() {
        return getJsonDataAsList("series");
    }

    //This method reads a json file and depending on the given type
    private List<Media> getJsonDataAsList(String type) {
        List<Media> media = new ArrayList<>();
        JsonObject object;
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(MEDIA_FILE));
            object = jsonElement.getAsJsonObject();

            for (JsonElement element : object.get(type).getAsJsonArray()) {
                Media med = new Media();
                med.setTitle(element.getAsJsonObject().get("title").getAsString());
                med.setGenre(element.getAsJsonObject().get("genre").getAsString());
                med.setYear(element.getAsJsonObject().get("year").getAsString());
                med.setRuntime(element.getAsJsonObject().get("runtime").getAsString());
                med.setRating(element.getAsJsonObject().get("rating").getAsString());
                media.add(med);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Unable to read file: " + MEDIA_FILE, e);
        }
        return media;
    }
}
