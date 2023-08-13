package gr.thanflix

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.repository.MoviesRepository
import gr.thanflix.base.BaseApplication
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import gr.thanflix.domain.models.base.Result


@HiltViewModel
class TestViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
): ViewModel() {

    fun fetchPopularMovies() {
        viewModelScope.launch(dispatcher) {

            when (val result = moviesRepository.getPopularMovies(page = 1)) {
                is Result.Success -> {
                    Timber.d("Movies .......... \n.......... \n.......... \n ${result.body?.results})")
                }
                is Result.Failure -> {
//                    handleErrors(result)
                }
            }

//            withContext(Dispatchers.Main) {
//                onTriggerEvent(LauncherEvents.BootstrapComplete)
//            }
        }
    }
}