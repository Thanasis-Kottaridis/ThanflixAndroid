package gr.thanflix.onboarding.ui.authorization

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.repository.SeriesRepository
import gr.thanflix.onboarding.coordinator.AuthorizationAction
import gr.thanflix.onboarding.ui.authorization.interactors.LoginEvents
import gr.thanflix.onboarding.ui.authorization.interactors.LoginState
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.navigation.GoToMainApp
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
    override var actionHandler: BaseActionHandler?,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
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
            is LoginEvents.GoToMainApp -> actionHandler?.handleAction(GoToMainApp)
        }
    }
}
