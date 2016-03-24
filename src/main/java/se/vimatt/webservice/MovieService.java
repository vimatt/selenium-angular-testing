package se.vimatt.webservice;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victormattsson on 2016-03-22.
 */
public class MovieService {

    List<Movie> movies = new ArrayList<>();

    public MovieService() {
        movies = importMovieListFromFile();
    }

    public List<Movie> getAllMovies() {
        return movies;
    }

    public List<Movie> importMovieListFromFile() {
        List<Movie> movies = new ArrayList<>();
        JsonObject object;
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader("src/main/resources/movies.json"));
            object = jsonElement.getAsJsonObject();

            for (JsonElement element : object.get("movies").getAsJsonArray()) {
                Movie movie = new Movie();
                movie.setTitle(element.getAsJsonObject().get("title").getAsString());
                movie.setGenre(element.getAsJsonObject().get("genre").getAsString());
                movie.setYear(element.getAsJsonObject().get("year").getAsString());
                movie.setRuntime(element.getAsJsonObject().get("runtime").getAsString());
                movie.setRating(element.getAsJsonObject().get("rating").getAsString());
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
