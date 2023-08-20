package gr.thanflix.data.repository

import gr.thanflix.data.network.api.TMDBApi
import gr.thanflix.data.network.dto.series.SeriesDetailsDto
import gr.thanflix.data.network.mappers.movies.MoviePagingMapper
import gr.thanflix.data.network.mappers.series.SeriesDetailsMapper
import gr.thanflix.data.network.mappers.series.SeriesPagingMapper
import gr.thanflix.data.utils.DataProvider
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.PagedGenericResponse
import gr.thanflix.domain.models.base.PagedListResult
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.repository.SeriesRepository
import javax.inject.Inject

class SeriesRepositoryImpl @Inject constructor(
    private val api: TMDBApi
): SeriesRepository {
    override suspend fun getAiringTodaySeries(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getAiringTodaySeries(page = page) },
            apiMapper = { SeriesPagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getOnTheAirSeries(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getOnTheAirSeries(page = page) },
            apiMapper = { SeriesPagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getPopularSeries(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getPopularSeries(page = page) },
            apiMapper = { SeriesPagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getTopRatedSeries(page: Int): Result<PagedListResult<Show>?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getTopRatedSeries(page = page) },
            apiMapper = { SeriesPagingMapper().domainToPagingData(it ?: PagedGenericResponse()) }
        )
            .build()
            .execute()
    }

    override suspend fun getSeriesDetails(seriesId: Int): Result<ShowDetails?, BaseException> {
        return DataProvider.Builder(
            apiCall = { api.getSeriesDetails(seriesId = seriesId) },
            apiMapper = { SeriesDetailsMapper().modelToDomain(it ?: SeriesDetailsDto()) }
        )
            .build()
            .execute()
    }
}