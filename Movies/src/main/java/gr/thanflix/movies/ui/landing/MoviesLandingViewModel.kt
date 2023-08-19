package gr.thanflix.movies.ui.landing

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.repository.MoviesRepository
import gr.thanflix.movies.ui.landing.interactors.MoviesLandingEvents
import gr.thanflix.movies.ui.landing.interactors.MoviesLandingState
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
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
            is MoviesLandingEvents.FetchData -> fetchPopularMovies()
        }
    }

    private fun fetchPopularMovies() {
        viewModelScope.launch(dispatcher) {

            when (val result = moviesRepository.getPopularMovies(page = 1)) {
                is Result.Success -> {
                    mState.tryEmit(
                        mState.value.copy(
                            popularMovies = result.body?.results ?: listOf()
                        )
                    )
                    Timber.d("Movies .......... \n.......... \n.......... \n ${result.body?.results})")
                }

                is Result.Failure -> {
                    handleErrors(result.errorBody)
                }
            }
        }
    }
}