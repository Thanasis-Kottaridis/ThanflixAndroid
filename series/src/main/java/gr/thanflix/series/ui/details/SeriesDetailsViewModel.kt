package gr.thanflix.series.ui.details

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.Result
import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.domain.repository.SeriesRepository
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.navigation.PopAction
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import gr.thanflix.presentation.utils.helpers.withDelay
import gr.thanflix.series.ui.details.interactors.SeriesDetailsEvents
import gr.thanflix.series.ui.details.interactors.SeriesDetailsState
import gr.thanflix.series.util.Extras
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?,
    private val seriesRepository: SeriesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
    ): BaseViewModel<SeriesDetailsState, SeriesDetailsEvents>(context, baseErrorDispatcher) {

    override var mState: MutableStateFlow<SeriesDetailsState> = MutableStateFlow(SeriesDetailsState())
    override val state: StateFlow<SeriesDetailsState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: SeriesDetailsEvents) {
        when(event) {
            is SeriesDetailsEvents.SetSeriesId ->  mState.tryEmit(mState.value.copy(seriesId = event.id))
            is SeriesDetailsEvents.FetchData -> fetchSeriesDetails()
            is SeriesDetailsEvents.GoBack -> actionHandler?.handleAction(PopAction)
        }
    }

    private fun fetchSeriesDetails() {
        // Show loader
        mState.tryEmit(mState.value.copy(isLoading = true))
        viewModelScope.launch(dispatcher) {

            when (val result = seriesRepository.getSeriesDetails(seriesId = mState.value.seriesId)) {
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