package gr.thanflix.onboarding.coordinator

import android.content.Context
import androidx.navigation.NavController
import gr.thanflix.onboarding.R
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator

sealed class OnboardingAction: Action {
    object GoToUserDetails: OnboardingAction()
    object GoToUserAddress : OnboardingAction()
}

class OnboardingCoordinator(
    override var context: Context?,
    override var navController: NavController?
): Coordinator {
    override val graphId: Int = R.id.onboarding_nav_graph

    override fun start() {
        navigate(graphId)
    }

    override fun handleAction(action: Action) {
        when(action) {
            is OnboardingAction.GoToUserDetails -> navigate(R.id.onboardingDetailsFragment)
            is OnboardingAction.GoToUserAddress -> navigate(R.id.onboardingAddressFragment)
        }
    }
}