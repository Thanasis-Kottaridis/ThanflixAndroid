package gr.thanflix.series.ui.landing


import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.repository.SeriesRepository
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import gr.thanflix.series.coordinator.SeriesAction
import gr.thanflix.series.ui.landing.interactors.SeriesLandingEvents
import gr.thanflix.series.ui.landing.interactors.SeriesLandingState
import gr.thanflix.series.util.Extras
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SeriesLandingViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?,
    private val seriesRepository: SeriesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<SeriesLandingState, SeriesLandingEvents>(context, baseErrorDispatcher) {

    override var mState: MutableStateFlow<SeriesLandingState> =
        MutableStateFlow(SeriesLandingState())
    override val state: StateFlow<SeriesLandingState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: SeriesLandingEvents) {
        when (event) {
            is SeriesLandingEvents.FetchData -> fetchAllSeries()
            is SeriesLandingEvents.SelectSeries -> actionHandler?.handleAction(
                SeriesAction.GoToSeriesDetails(
                    id = event.id
                )
            )

        }
    }

    private fun fetchAllSeries() {
        viewModelScope.launch(dispatcher) {
            // Show loader
            mState.tryEmit(mState.value.copy(isLoading = true))

            val requests = listOf(
                async { fetchTodaySeries(page = 1) },
                async { fetchOnTheAirSeries(page = 1) },
                async { fetchPopularSeries(page = 1) },
                async { fetchTopRatedSeries(page = 1) }
            )

            requests.awaitAll() // Wait for all requests to complete

            // Hide loader
            mState.tryEmit(mState.value.copy(isLoading = false))
        }
    }

    private suspend fun fetchTodaySeries(page: Int) {
        when (val result = seriesRepository.getTopRatedSeries(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        todaySeries = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }

    private suspend fun fetchOnTheAirSeries(page: Int) {
        when (val result = seriesRepository.getOnTheAirSeries(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        onTheAirSeries = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }

    private suspend fun fetchPopularSeries(page: Int) {
        when (val result = seriesRepository.getPopularSeries(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        popularSeries = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }

    private suspend fun fetchTopRatedSeries(page: Int) {
        when (val result = seriesRepository.getTopRatedSeries(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        topRatedSeries = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }
}