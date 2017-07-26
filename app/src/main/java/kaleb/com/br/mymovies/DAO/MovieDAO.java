package kaleb.com.br.mymovies.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kaleb.com.br.mymovies.Model.Movie;

/**
 * Created by kaleb on 25/07/2017.
 */
@Dao
public interface MovieDAO {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Query("SELECT * FROM movie WHERE imdbID IN (:movieIds)")
    List<Movie> loadAllByIds(int[] movieIds);

    @Query("SELECT * FROM movie WHERE Title LIKE :title ")
    List<Movie> findByTitle(String title);

    @Query("SELECT count(*) FROM movie WHERE imdbID=:imdbid")
    int countById(String imdbid);
    @Insert
    void insertAll(Movie... movie);

    @Delete
    void delete(Movie movie);

    @Update
    void update(Movie movie);

    @Query("SELECT * FROM movie WHERE imdbID=:imdbid")
    Movie getItemById(String imdbid);


}
