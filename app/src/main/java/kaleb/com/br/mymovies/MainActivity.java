package kaleb.com.br.mymovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import java.io.IOException;
import java.util.ArrayList;

import kaleb.com.br.mymovies.Adapters.MovieAdapter;
import kaleb.com.br.mymovies.Application.CustomApplication;
import kaleb.com.br.mymovies.Listeners.MovieAdapterListener;
import kaleb.com.br.mymovies.Listeners.ResponseListMoviesRetrofit;
import kaleb.com.br.mymovies.Model.Movie;
import kaleb.com.br.mymovies.Util.CustomAppCompatActivity;

public class MainActivity extends CustomAppCompathAcitivityKot {


    private ArrayList<Movie> listaFilmes;
    private RecyclerView.LayoutManager mLayoutManager;
    private MovieAdapter movieAdapter;
    private RecyclerView mRecyclerView;

    private CustomApplication application;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MyMovies");
        application = (CustomApplication)getApplicationContext();
        findViews();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(!application.isNetworkAvailable()){
                    View parentLayout = findViewById(android.R.id.content);
                    openSnackBar(parentLayout,"Sem conexao com a internet");
                    searchInMyFavoritsMovies(newText);

                }else
                if(!newText.trim().equals("")){
                    searchByText(newText);
                }else{
                    setAdapterRec(null);
                }

                return false;
            }
        });
        return true;
    }

    private void searchInMyFavoritsMovies(String newText) {
        setAdapterRec((ArrayList<Movie>) application.getDb().userDao().findByTitle("%"+newText+"%"));

    }

    private void searchByText(String newText) {
        try {
            application.getApiOmdb(newText, new ResponseListMoviesRetrofit() {
                @Override
                public void onSuccess(ArrayList<Movie> lista) {

                    setAdapterRec(lista);
                }

                @Override
                public void onError() {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //listaFilmes =  application.getApiOmdb(newText);
        if(listaFilmes==null)
            listaFilmes= new ArrayList<Movie>();

        setAdapterRec(listaFilmes);
    }

    private void setAdapterRec(ArrayList<Movie> listaFilmes) {
        if(listaFilmes==null){
            listaFilmes = (ArrayList<Movie>) application.getDb().userDao().getAll();
        }
        movieAdapter = new MovieAdapter(this, listaFilmes, new MovieAdapterListener() {
            @Override
            public void onClickFabFavorit(Movie movie) {
                application.favoritityMovie(movie);
            }

            @Override
            public void onClickItemList(Movie movie) {
                Intent it = new Intent(MainActivity.this,ScrollingActivity.class);
                it.putExtra("omdbId",movie.getImdbID());
                startActivity(it);
            }
        });
        mRecyclerView.setAdapter(movieAdapter);
    }

    private void findViews() {

        mRecyclerView = (RecyclerView) findViewById(R.id. rvMovies);
        mLayoutManager  =new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setAdapterRec(listaFilmes);

    }

}
