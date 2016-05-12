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
 * Class that provides the webservice data from Json file
 *
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
            //A JsonParser that tries to read and parse the given file to a JsonElement
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(MEDIA_FILE));
            object = jsonElement.getAsJsonObject();

            //Iterates through the specific JsonElement, which is determined by the given type
            for (JsonElement element : object.get(type).getAsJsonArray()) {
                //Creates a new Media object and sets the different values from the json object
                Media med = new Media();
                med.setTitle(element.getAsJsonObject().get("title").getAsString());
                med.setGenre(element.getAsJsonObject().get("genre").getAsString());
                med.setYear(element.getAsJsonObject().get("year").getAsString());
                med.setRuntime(element.getAsJsonObject().get("runtime").getAsString());
                med.setRating(element.getAsJsonObject().get("rating").getAsString());
                //Adds the media object to the media List
                media.add(med);
            }
        } catch (IOException e) {
            logger.error("Unable to read file: " + MEDIA_FILE, e);
        }
        //Returns the media List
        return media;
    }
}
