package kaleb.com.br.mymovies.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kaleb on 25/07/2017.
 */

public class MovieGsonResult {
    @SerializedName("Search")
    public List<Movie> movies;
}
