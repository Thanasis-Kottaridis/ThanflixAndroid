package gr.thanflix.onboarding.ui.authorization.interactors

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.onboarding.coordinator.AuthorizationAction
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?
) : BaseViewModel<LoginState, LoginEvents>(context, baseErrorDispatcher) {

    override var mState: MutableStateFlow<LoginState> =
        MutableStateFlow(LoginState())
    override val state: StateFlow<LoginState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: LoginEvents) {
        when (event) {
            is LoginEvents.GoToOnboarding -> actionHandler?.handleAction(AuthorizationAction.GoToOnboarding)
        }
    }
}