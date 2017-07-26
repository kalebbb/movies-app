package kaleb.com.br.mymovies.Listeners

import kaleb.com.br.mymovies.Model.Movie

/**
 * Created by kaleb on 26/07/2017.
 */
interface ReturnGetMovieListener {
    fun onSuccess(filme: Movie)
}