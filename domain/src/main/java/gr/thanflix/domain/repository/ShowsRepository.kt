package gr.thanflix.domain.repository

import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.PagedListResult
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.models.base.Result
interface ShowsRepository {
    // Series Endpoints
    suspend fun getAiringTodaySeries(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getOnTheAirSeries(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getPopularSeries(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getTopRatedSeries(page: Int): Result<PagedListResult<Show>?, BaseException>
    suspend fun getSeriesDetails(seriesId: Int): Result<ShowDetails?, BaseException>
}