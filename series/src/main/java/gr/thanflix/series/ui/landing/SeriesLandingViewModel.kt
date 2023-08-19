package gr.thanflix.series.ui.landing


import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import gr.thanflix.series.coordinator.SeriesAction
import gr.thanflix.series.ui.landing.interactors.SeriesLandingEvents
import gr.thanflix.series.ui.landing.interactors.SeriesLandingState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SeriesLandingViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?
): BaseViewModel<SeriesLandingState, SeriesLandingEvents>(context, baseErrorDispatcher){

    override var mState: MutableStateFlow<SeriesLandingState> = MutableStateFlow(SeriesLandingState())
    override val state: StateFlow<SeriesLandingState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: SeriesLandingEvents) {
        when(event) {
           is SeriesLandingEvents.SelectSeries -> actionHandler?.
           handleAction(SeriesAction.GoToSeriesDetails(id = event.id))
        }
    }
}