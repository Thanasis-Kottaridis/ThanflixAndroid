package gr.thanflix.onboarding.ui.onboarding.user.address.interactors

import gr.thanflix.presentation.base.viewModel.BaseState

data class OnboardingAddressState(
    override val isLoading: Boolean = false,
    override val isOnline: Boolean = true
): BaseState()