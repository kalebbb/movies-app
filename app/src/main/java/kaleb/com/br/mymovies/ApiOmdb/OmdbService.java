package kaleb.com.br.mymovies.ApiOmdb;

import java.util.List;

import kaleb.com.br.mymovies.Model.Movie;
import kaleb.com.br.mymovies.Model.MovieGsonResult;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by kaleb on 25/07/2017.
 */

public interface OmdbService {

    @POST("./")
    Call<MovieGsonResult> getMoviesBySearch(@Query("apikey") String apikey, @Query("s") String query,@Query("r") String format);

    @POST("./")
    Call<Movie> getMovieById(@Query("apikey") String apikey, @Query("i") String query,@Query("r") String format);

}
