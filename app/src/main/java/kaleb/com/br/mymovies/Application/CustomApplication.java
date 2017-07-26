package kaleb.com.br.mymovies.Application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kaleb.com.br.mymovies.ApiOmdb.OmdbService;
import kaleb.com.br.mymovies.DataBase.AppDatabase;
import kaleb.com.br.mymovies.Listeners.ResponseListMoviesRetrofit;
import kaleb.com.br.mymovies.Listeners.ReturnGetMovieListener;
import kaleb.com.br.mymovies.Model.Movie;
import kaleb.com.br.mymovies.Model.MovieGsonResult;
import kaleb.com.br.mymovies.Util.MovieDeserializer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by kaleb on 25/07/2017.
 */

public class CustomApplication extends Application {
    private AppDatabase db;
    private Retrofit apiOmdb;
    private OmdbService service;
    public void getMovie(String omdbId, final ReturnGetMovieListener listener){
        Movie movie= db.userDao().getItemById(omdbId);
        if(movie==null){
            Gson gson = new GsonBuilder().registerTypeAdapter(Movie.class,new MovieDeserializer()).create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create( gson ))
                    .build();

            final OmdbService service = retrofit.create(OmdbService.class);
            Call<Movie> movies =service.getMovieById("ec6483bd",omdbId,"json");


            movies.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {
                    if(call!=null){
                        listener.onSuccess(response.body());
                    }
                }
                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    if(call==null){

                    }
                }
            });

        }else{
            listener.onSuccess(movie);
        }

    }
    public void getApiOmdb(String textSearch, final ResponseListMoviesRetrofit listener) throws IOException {
        Gson gson = new GsonBuilder().registerTypeAdapter(Movie.class,new MovieDeserializer()).create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create( gson ))
                .build();

        final OmdbService service = retrofit.create(OmdbService.class);
        Call<MovieGsonResult> movies =service.getMoviesBySearch("ec6483bd",textSearch,"json");

        //Call<List<Movie>> movies = service.search("ec6483bd","ga","json");
        movies.enqueue(new Callback<MovieGsonResult>() {
            @Override
            public void onResponse(Call<MovieGsonResult> call, Response<MovieGsonResult> response) {
                if(call!=null){
                        listener.onSuccess((ArrayList<Movie>) response.body().movies);
                }
            }
            @Override
            public void onFailure(Call<MovieGsonResult> call, Throwable t) {
                if(call==null){

                }
            }
        });

    }

    public void setApiOmdb(Retrofit apiOmdb) {
        this.apiOmdb = apiOmdb;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    private void setDbifNull(){
        if(db==null){
            db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "database-name").allowMainThreadQueries().build();
        }
    }

    public AppDatabase getDb() {
        setDbifNull();
        return db;
    }

    public void setDb(AppDatabase db) {
        setDbifNull();
        this.db = db;
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public void favoritityMovie(Movie movie) {
        if(db.userDao().countById(movie.getImdbID())<1){
            getMovie(movie.getImdbID(), new ReturnGetMovieListener() {
                @Override
                public void onSuccess(Movie filme) {
                    db.userDao().insertAll(filme);
                }
            });

        }else{
            db.userDao().delete(movie);
        }

    }
}
