package gr.thanflix

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.repository.MoviesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.models.show.Show
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseState
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TestState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true,
    val nowPlayingMovies: List<Show> = listOf(),
    val popularMovies: List<Show> = listOf(),
    val topRatedMovies: List<Show> = listOf(),
    val upcomingMovies: List<Show> = listOf()
): BaseState()

sealed class TestEvents {
    object FetchPopularMovies: TestEvents()
}

@HiltViewModel
class TestViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
): BaseViewModel<TestState, TestEvents>(context, baseErrorDispatcher) {

    override var actionHandler: BaseActionHandler? = null

    override var mState: MutableStateFlow<TestState> = MutableStateFlow(TestState())
    override val state: StateFlow<TestState>
    get() = mState.asStateFlow()

    init {
        commonInit()
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

    override fun commonInit() {
        Timber.d("Movies .......... \n" +
                ".......... \n" +
                ".......... \n" +
                "Common Init Called")
    }

    override fun onTriggerEvent(event: TestEvents) {
        when (event) {
            is TestEvents.FetchPopularMovies -> fetchPopularMovies()
        }
    }
}