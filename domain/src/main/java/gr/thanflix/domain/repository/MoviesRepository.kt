package gr.thanflix.domain.repository

import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.PagedListResult
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.models.base.Result

interface MoviesRepository {

    // Movies Endpoints
    suspend fun getNowPlayingMovies(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getPopularMovies(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getTopRatedMovies(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getUpcomingMovies(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getMovieDetails(movieId: Int): Result<ShowDetails?, BaseException>

}