package gr.thanflix.presentation.base.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.presentation.base.navigation.BaseActionDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel<State: BaseState, Event> (
    private val context: Context,
    private val baseErrorDispatcher: BaseErrorDispatcher

): ViewModel(),
    BaseActionDispatcher,
    BaseErrorHandler {

//    abstract var state: SharedFlow<State>
//    protected abstract var mutableState: MutableSharedFlow<State>
    protected abstract var mState: MutableStateFlow<State>
    abstract val state: StateFlow<State>

    abstract fun commonInit()
    abstract fun onTriggerEvent(event: Event)

    override fun handleErrors(
        error: BaseException,
        config: HandleErrorsConfig
    ): Boolean {
        return baseErrorDispatcher.handleErrors(
            actionHandler,
            error,
            config
        )
    }
}