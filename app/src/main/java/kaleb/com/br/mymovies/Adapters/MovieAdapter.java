package kaleb.com.br.mymovies.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kaleb.com.br.mymovies.Application.CustomApplication;
import kaleb.com.br.mymovies.Listeners.MovieAdapterListener;
import kaleb.com.br.mymovies.Model.Movie;
import kaleb.com.br.mymovies.R;

/**
 * Created by kaleb on 25/07/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Movie> listaFilmes;
    private Context ctx;
    private CustomApplication application;
    private MovieAdapterListener movieAdapterListener;
    public MovieAdapter (Context context, ArrayList<Movie> list, MovieAdapterListener movieAdapterListener){
        this.mInflater = LayoutInflater.from(context);
        this.ctx = context;
        this.movieAdapterListener = movieAdapterListener;
        if(list==null){
            listaFilmes=new ArrayList<>();
        }
        application = (CustomApplication) ctx.getApplicationContext();
        //application.getDb().userDao().getAll();

        listaFilmes = list;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_card_movie,parent,false);
        MovieHolder holder= new MovieHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        final Movie movie = listaFilmes.get(position);
        Picasso.with(ctx).load(movie.getBannerUrl())
                .into(holder.imgBanner);

        holder.rtAvaliacao.setRating((movie.getRating()/2));
        holder.txvGenre.setText(movie.getGenre());
        holder.txvRuntime.setText(movie.getRunTime());
        holder.txvTitle.setText(movie.getTitle());
        holder.tipo.setText(movie.getType());
        holder.txvYear.setText(movie.getYear());
        if(application.getDb().userDao().countById(movie.getImdbID())>0){
            holder.fabFavorit.setBackgroundColor(Color.RED);
            holder.fabFavorit.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            holder.fabFavorit.setImageResource(R.drawable.ic_heart_white_24dp);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movieAdapterListener.onClickItemList(movie);
            }
        });
        holder.fabFavorit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.fabFavorit.getBackgroundTintList()!=ColorStateList.valueOf(Color.RED)){
                    holder.fabFavorit.setBackgroundColor(Color.RED);
                    holder.fabFavorit.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    holder.fabFavorit.setImageResource(R.drawable.ic_heart_white_24dp);
                }else{

                    holder.fabFavorit.setBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
                    holder.fabFavorit.setImageResource(R.drawable.ic_heart_black_24dp);
                }
                movieAdapterListener.onClickFabFavorit(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaFilmes==null? 0:listaFilmes.size() ;
    }

    class MovieHolder extends RecyclerView.ViewHolder{
        private FloatingActionButton fabFavorit;
        private ImageView imgBanner;
        private RatingBar rtAvaliacao;
        private TextView txvTitle;
        private TextView txvRuntime;
        private TextView txvGenre;
    private TextView tipo;
        private TextView txvYear;
        private ConstraintLayout itemView;
        /**
         * Find the Views in the layout<br />
         * <br />
         * Auto-created on 2017-07-25 15:10:16 by Android Layout Finder
         * (http://www.buzzingandroid.com/tools/android-layout-finder)
         */
        private void findViews(View view) {
            itemView = (ConstraintLayout)view.findViewById(R.id.teste2);
            fabFavorit = (FloatingActionButton)view.findViewById( R.id.fabFavorit );
            imgBanner = (ImageView)view.findViewById( R.id.imgBanner );
            rtAvaliacao = (RatingBar)view.findViewById( R.id.rtAvaliacao );
            txvTitle = (TextView)view.findViewById( R.id.txvTitle );
            txvRuntime = (TextView)view.findViewById( R.id.txvRuntime );
            txvGenre = (TextView)view.findViewById( R.id.txvGenre );
            tipo = (TextView)view.findViewById( R.id.tipo );
            txvYear= (TextView)view.findViewById( R.id.txvYear );
            //fabFavorit.setOnClickListener( this );
        }


        public MovieHolder(View itemView) {
            super(itemView);
            findViews(
                    itemView
            );
        }
    }
}
