package kaleb.com.br.mymovies.Listeners;

import java.util.ArrayList;

import kaleb.com.br.mymovies.Model.Movie;

/**
 * Created by kaleb on 25/07/2017.
 */

public interface ResponseListMoviesRetrofit {
    void onSuccess(ArrayList<Movie> lista);
    void onError();
}
