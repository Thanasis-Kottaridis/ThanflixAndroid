package gr.thanflix.onboarding.ui.authorization.interactors

import gr.thanflix.presentation.base.viewModel.BaseState

data class LoginState(
    override val isLoading: Boolean = false,
    override val isOnline: Boolean = true
): BaseState()