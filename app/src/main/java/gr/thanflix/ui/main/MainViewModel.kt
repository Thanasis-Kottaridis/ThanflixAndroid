package gr.thanflix.ui.main

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.coordinator.GoToMoviesTab
import gr.thanflix.coordinator.GoToSeriesTab
import timber.log.Timber
import javax.inject.Inject
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import gr.thanflix.ui.main.interactors.MainEvents
import gr.thanflix.ui.main.interactors.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?
): BaseViewModel<MainState, MainEvents>(context, baseErrorDispatcher) {

    override var mState: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    override val state: StateFlow<MainState>
    get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun commonInit() {
        Timber.d("Movies .......... \n" +
                ".......... \n" +
                ".......... \n" +
                "Common Init Called")
    }

    override fun onTriggerEvent(event: MainEvents) {
        when(event) {
            is MainEvents.SelectMoviesTab -> actionHandler?.handleAction(GoToMoviesTab())
            is MainEvents.SelectSeriesTab -> actionHandler?.handleAction(GoToSeriesTab())
        }
    }
}