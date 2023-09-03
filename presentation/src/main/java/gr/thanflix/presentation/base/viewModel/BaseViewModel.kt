package gr.thanflix.presentation.base.viewModel

import android.content.Context
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.presentation.base.navigation.BaseActionDispatcher
import gr.thanflix.presentation.base.navigation.HideLoaderAction
import gr.thanflix.presentation.base.navigation.ShowLoaderAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : BaseState, Event>(
    private val context: Context,
    private val baseErrorDispatcher: BaseErrorDispatcher
) : ViewModel(),
    BaseActionDispatcher,
    BaseErrorHandler {

    protected abstract var mState: MutableStateFlow<State>
    abstract val state: StateFlow<State>

    open fun commonInit() {

        // Observe the state changes to check isLoading
        viewModelScope.launch {
            mState.map { it.isLoading }.distinctUntilChanged().collect {
                if (it) {
                    actionHandler?.handleAction(ShowLoaderAction)
                } else {
                    actionHandler?.handleAction(HideLoaderAction)
                }
            }
        }
    }

    private fun showLoader() {
        if (mState.value.isLoading) {
            actionHandler?.handleAction(ShowLoaderAction)
        } else {
            actionHandler?.handleAction(HideLoaderAction)
        }
    }

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