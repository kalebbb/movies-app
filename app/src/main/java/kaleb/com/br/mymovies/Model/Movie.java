package kaleb.com.br.mymovies.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaleb on 25/07/2017.
 */

@Entity
public class Movie {
    @PrimaryKey
    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @SerializedName("Type")
    @ColumnInfo(name = "Type")
    @Expose
    private String type;


    @SerializedName("Actors")
    @ColumnInfo(name = "Actors")
    @Expose
    private String actors;

    @SerializedName("Title")
    @ColumnInfo(name = "Title")
    @Expose
    private String title;

    @SerializedName("Director")
    @ColumnInfo(name = "Director")
    @Expose
    private String director;

    @SerializedName("Poster")
    @ColumnInfo(name = "Poster")
    @Expose
    private String bannerUrl;

    @SerializedName("Plot")
    @ColumnInfo(name = "Plot")
    @Expose
    private String description;

    @SerializedName("Runtime")
    @ColumnInfo(name = "Runtime")
    @Expose
    private String runTime;

    @SerializedName("Genre")
    @ColumnInfo(name = "Genre")
    @Expose
    private String genre;

    @SerializedName("Year")
    @ColumnInfo(name = "Year")
    @Expose
    private String year;

    @SerializedName("imdbRating")
    @ColumnInfo(name = "imdbRating")
    @Expose
    private float rating;

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }
}
