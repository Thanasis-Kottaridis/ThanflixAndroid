package gr.thanflix.series.ui.details

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.navigation.PopAction
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import gr.thanflix.series.coordinator.SeriesAction
import gr.thanflix.series.ui.details.interactors.SeriesDetailsEvents
import gr.thanflix.series.ui.details.interactors.SeriesDetailsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?
): BaseViewModel<SeriesDetailsState, SeriesDetailsEvents>(context, baseErrorDispatcher){

    override var mState: MutableStateFlow<SeriesDetailsState> = MutableStateFlow(SeriesDetailsState())
    override val state: StateFlow<SeriesDetailsState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: SeriesDetailsEvents) {
        when(event) {
            is SeriesDetailsEvents.GoBack -> actionHandler?.handleAction(PopAction)
            is SeriesDetailsEvents.GoToTest -> actionHandler?.
            handleAction(SeriesAction.GoToTest)
        }
    }
}