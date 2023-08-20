package gr.thanflix.data.network.api

import gr.thanflix.data.network.dto.movies.MovieDetailsDto
import gr.thanflix.data.network.dto.movies.MovieDto
import gr.thanflix.data.network.dto.series.SeriesDetailsDto
import gr.thanflix.data.network.dto.series.SeriesDto
import gr.thanflix.domain.models.base.PagedGenericResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {

    /**
     * Movies Endpoints
     */
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<PagedGenericResponse<List<MovieDto>>>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ):  Response<PagedGenericResponse<List<MovieDto>>>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): Response<PagedGenericResponse<List<MovieDto>>>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ):  Response<PagedGenericResponse<List<MovieDto>>>

    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId: Int
    ): Response<MovieDetailsDto>

    /**
     * Series Endpoints
     */
    @GET("tv/airing_today")
    suspend fun getAiringTodaySeries(
        @Query("page") page: Int
    ): Response<PagedGenericResponse<List<SeriesDto>>>

    @GET("tv/on_the_air")
    suspend fun getOnTheAirSeries(
        @Query("page") page: Int
    ): Response<PagedGenericResponse<List<SeriesDto>>>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(
        @Query("page") page: Int
    ): Response<PagedGenericResponse<List<SeriesDto>>>

    @GET("tv/popular")
    suspend fun getPopularSeries(
        @Query("page") page: Int
    ): Response<PagedGenericResponse<List<SeriesDto>>>

    @GET("tv/{seriesId}")
    suspend fun getSeriesDetails(
        @Path("seriesId") seriesId: Int
    ): Response<SeriesDetailsDto>
}