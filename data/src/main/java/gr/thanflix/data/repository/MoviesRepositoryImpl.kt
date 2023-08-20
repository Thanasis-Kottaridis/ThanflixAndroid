package gr.thanflix.data.repository

import gr.thanflix.data.network.api.TMDBApi
import gr.thanflix.data.network.dto.movies.MovieDetailsDto
import gr.thanflix.data.network.mappers.movies.MovieDetailsMapper
import gr.thanflix.data.network.mappers.movies.MoviePagingMapper
import gr.thanflix.data.utils.DataProvider
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.PagedGenericResponse
import gr.thanflix.domain.models.base.PagedListResult
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: TMDBApi
): MoviesRepository {
    override suspend fun getNowPlayingMovies(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getNowPlayingMovies(page = page) },
            apiMapper = { MoviePagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getPopularMovies(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getPopularMovies(page = page) },
            apiMapper = { MoviePagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getTopRatedMovies(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getTopRatedMovies(page = page) },
            apiMapper = { MoviePagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getUpcomingMovies(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getUpcomingMovies(page = page) },
            apiMapper = { MoviePagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getMovieDetails(movieId: Int): Result<ShowDetails?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getMovieDetails(movieId = movieId) },
            apiMapper = { MovieDetailsMapper().modelToDomain(it ?: MovieDetailsDto()) }
        )
            .build()
            .execute()
    }

}