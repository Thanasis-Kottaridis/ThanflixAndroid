package gr.thanflix.onboarding.coordinator

import android.content.Context
import android.os.Bundle
import androidx.navigation.NavController
import gr.thanflix.onboarding.R
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator

sealed class AuthorizationAction: Action {
    object GoToLogin: AuthorizationAction()
    object GoToOnboarding : AuthorizationAction()
}

class AuthorizationCoordinator(
    override var context: Context?,
    override var navController: NavController?
): Coordinator {
    override val graphId: Int = R.id.authorization_nav_graph

    override fun start() {
        navigate(graphId)
    }

    override fun handleAction(action: Action) {
        when(action) {
            is AuthorizationAction.GoToLogin -> navigate(R.id.loginFragment)

            is AuthorizationAction.GoToOnboarding -> {
                val coordinator = OnboardingCoordinator(context, navController)
                coordinator.start()
            }
        }
    }
}