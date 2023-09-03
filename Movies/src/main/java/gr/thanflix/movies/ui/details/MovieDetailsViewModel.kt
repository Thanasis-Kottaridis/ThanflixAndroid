package gr.thanflix.movies.ui.details

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.repository.MoviesRepository
import gr.thanflix.movies.ui.details.interactors.MovieDetailsEvents
import gr.thanflix.movies.ui.details.interactors.MovieDetailsState
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.navigation.PopAction
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import gr.thanflix.presentation.utils.helpers.withDelay
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?,
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): BaseViewModel<MovieDetailsState, MovieDetailsEvents>(context, baseErrorDispatcher)  {

    override var mState: MutableStateFlow<MovieDetailsState> = MutableStateFlow(MovieDetailsState())
    override val state: StateFlow<MovieDetailsState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: MovieDetailsEvents) {
        when(event) {
            is MovieDetailsEvents.SetMovieId ->  mState.tryEmit(mState.value.copy(movieId = event.id))
            is MovieDetailsEvents.FetchData -> fetchMovieDetails()
            is MovieDetailsEvents.GoBack -> actionHandler?.handleAction(PopAction)
        }
    }

    private fun fetchMovieDetails() {
        // Show loader
        mState.tryEmit(mState.value.copy(isLoading = true))

        viewModelScope.launch(dispatcher) {
            when (val result = moviesRepository.getMovieDetails(movieId = mState.value.movieId)) {
                is Result.Success -> {
                    mState.tryEmit(
                        mState.value.copy(
                            details = result.body ?: ShowDetails()
                        )
                    )
                }

                is Result.Failure -> {
                    handleErrors(result.errorBody)
                }
            }

            // Hide loader
            mState.tryEmit(mState.value.copy(isLoading = false))
        }
    }
}