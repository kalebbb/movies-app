package kaleb.com.br.mymovies.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import kaleb.com.br.mymovies.DAO.MovieDAO;
import kaleb.com.br.mymovies.Model.Movie;

/**
 * Created by kaleb on 25/07/2017.
 */
@Database(entities = {Movie.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDAO userDao();
}
