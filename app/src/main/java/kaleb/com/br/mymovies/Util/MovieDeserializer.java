package kaleb.com.br.mymovies.Util;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import kaleb.com.br.mymovies.Model.Movie;

/**
 * Created by kaleb on 25/07/2017.
 */

public class MovieDeserializer implements JsonDeserializer<Object> {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonElement movies = json.getAsJsonObject();
        if(json.getAsJsonObject().get("Search")!=null){
            movies = json.getAsJsonObject().get("Search");
        }

        return new Gson().fromJson(movies,Movie.class);
    }
}
