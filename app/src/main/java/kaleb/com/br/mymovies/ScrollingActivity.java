package kaleb.com.br.mymovies;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import kaleb.com.br.mymovies.Application.CustomApplication;
import kaleb.com.br.mymovies.Listeners.ReturnGetMovieListener;
import kaleb.com.br.mymovies.Model.Movie;
import kaleb.com.br.mymovies.Util.CustomAppCompatActivity;

public class ScrollingActivity extends CustomAppCompathAcitivityKot {
    private String omdbId;
    private CustomApplication application;
    private FloatingActionButton fab;
    private TextView txvDirector;
    private TextView txvActors;
    private TextView txvDesc;
    private RatingBar rbAvalia;
    private ImageView imgBanner;
    private Movie filme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        findViews();
        application = (CustomApplication) getApplicationContext();
        omdbId = getIntent().getStringExtra("omdbId");
        if (application.getDb().userDao().countById(omdbId) > 0) {
            fab.setBackgroundColor(Color.RED);
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            fab.setImageResource(R.drawable.ic_heart_white_24dp);


        }
        application.getMovie(omdbId, new ReturnGetMovieListener() {
            @Override
            public void onSuccess(Movie filme) {

                setView(filme);
            }
        });
        //setView(application.getDb().userDao().getItemById(omdbId));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            this.finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private void findViews() {
        txvDirector = (TextView)findViewById( R.id.txvDirector );
        txvActors = (TextView)findViewById( R.id.txvActors );
        txvDesc = (TextView)findViewById( R.id.txvDesc );
        imgBanner = (ImageView)findViewById(R.id.imgBanner);
        rbAvalia = (RatingBar)findViewById(R.id.rbAvalia);
        fab= (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                application.favoritityMovie(filme);
                switchStyleFab();
            }
        });
    }

    private void switchStyleFab() {
        if(fab.getBackgroundTintList()!=ColorStateList.valueOf(Color.RED)){
            fab.setBackgroundColor(Color.RED);
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            fab.setImageResource(R.drawable.ic_heart_white_24dp);
        }else{

            fab.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
            fab.setImageResource(R.drawable.ic_heart_black_24dp);
        }

    }

    public void setView(Movie view) {
        filme = view;
        getSupportActionBar().setTitle(view.getTitle());
        Picasso.with(this).load(view.getBannerUrl()).into(imgBanner);
        txvDirector.setText(view.getDirector());
        txvActors.setText(view.getActors());
        txvDesc.setText(view.getDescription());
        rbAvalia.setRating((view.getRating()/2));
    }
}
