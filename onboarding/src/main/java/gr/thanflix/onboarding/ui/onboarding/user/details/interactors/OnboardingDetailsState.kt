package gr.thanflix.onboarding.ui.onboarding.user.details.interactors

import gr.thanflix.presentation.base.viewModel.BaseState

data class OnboardingDetailsState(
    override val isLoading: Boolean = false,
    override val isOnline: Boolean = true
): BaseState()