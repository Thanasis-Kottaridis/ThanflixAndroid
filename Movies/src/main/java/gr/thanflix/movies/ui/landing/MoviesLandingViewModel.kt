package gr.thanflix.movies.ui.landing

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.repository.MoviesRepository
import gr.thanflix.movies.coordinator.MoviesAction
import gr.thanflix.movies.ui.landing.interactors.MoviesLandingEvents
import gr.thanflix.movies.ui.landing.interactors.MoviesLandingState
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
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
class MoviesLandingViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?,
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<MoviesLandingState, MoviesLandingEvents>(context, baseErrorDispatcher) {

    override var mState: MutableStateFlow<MoviesLandingState> =
        MutableStateFlow(MoviesLandingState())
    override val state: StateFlow<MoviesLandingState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: MoviesLandingEvents) {
        when (event) {
            is MoviesLandingEvents.FetchData -> fetchAllData()
            is MoviesLandingEvents.SelectMovie -> actionHandler?.handleAction(
                MoviesAction.GoToMovieDetails(
                    id = event.id
                )
            )
        }
    }

    private fun fetchAllData() {
        viewModelScope.launch(dispatcher) {
            // Show loader
            mState.tryEmit(mState.value.copy(isLoading = true))

            val requests = listOf(
                async { fetchNowPlayingMovies(page = 1) },
                async { fetchPopularMovies(page = 1) },
                async { fetchTopRatedMovies(page = 1) },
                async { fetchUpcomingMovies(page = 1) }
            )

            requests.awaitAll() // Wait for all requests to complete

            // Hide loader
            mState.tryEmit(mState.value.copy(isLoading = false))
        }
    }

    private suspend fun fetchNowPlayingMovies(page: Int) {
        when (val result = moviesRepository.getNowPlayingMovies(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        nowPlayingMovies = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }

    private suspend fun fetchPopularMovies(page: Int) {
        when (val result = moviesRepository.getPopularMovies(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        popularMovies = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }

    private suspend fun fetchTopRatedMovies(page: Int) {
        when (val result = moviesRepository.getTopRatedMovies(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        topRatedMovies = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }

    private suspend fun fetchUpcomingMovies(page: Int) {
        when (val result = moviesRepository.getUpcomingMovies(page = page)) {
            is Result.Success -> {
                mState.tryEmit(
                    mState.value.copy(
                        upcomingMovies = result.body?.results ?: listOf()
                    )
                )
            }

            is Result.Failure -> {
                handleErrors(result.errorBody)
            }
        }
    }
}