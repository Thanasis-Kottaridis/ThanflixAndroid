package gr.thanflix.movies.ui.landing

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media.MediaBrowserCompatUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.models.show.Show
import gr.thanflix.domain.repository.MoviesRepository
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseState
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

data class MoviesLandingState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true,
    val nowPlayingMovies: List<Show> = listOf(),
    val popularMovies: List<Show> = listOf(),
    val topRatedMovies: List<Show> = listOf(),
    val upcomingMovies: List<Show> = listOf()
): BaseState()

sealed class MoviesLandingEvents {
    object FetchData: MoviesLandingEvents()
}

class MoviesLandingViewModel(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<MoviesLandingState, MoviesLandingEvents> (context, baseErrorDispatcher) {

    override var actionHandler: BaseActionHandler? = null

    override var mState: MutableStateFlow<MoviesLandingState> = MutableStateFlow(MoviesLandingState())
    override val state: StateFlow<MoviesLandingState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: MoviesLandingEvents) {
        when (event) {
            is MoviesLandingEvents.FetchData -> fetchPopularMovies()
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch(dispatcher) {

            when (val result = moviesRepository.getPopularMovies(page = 1)) {
                is Result.Success -> {
                    mState.tryEmit(mState.value.copy(popularMovies = result.body?.results ?: listOf()))
                    Timber.d("Movies .......... \n.......... \n.......... \n ${result.body?.results})")
                }
                is Result.Failure -> {
                    handleErrors(result.errorBody)
                }
            }
        }
    }
}