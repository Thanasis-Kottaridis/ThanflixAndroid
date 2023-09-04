package gr.thanflix.coordinator

import android.content.Context
import android.graphics.Movie
import androidx.navigation.NavController
import dagger.hilt.android.qualifiers.ActivityContext
import gr.thanflix.R
import gr.thanflix.movies.coordinator.MoviesAction
import gr.thanflix.movies.coordinator.MoviesCoordinator
import gr.thanflix.onboarding.coordinator.AuthorizationAction
import gr.thanflix.onboarding.coordinator.AuthorizationCoordinator
import gr.thanflix.onboarding.coordinator.OnboardingAction
import gr.thanflix.onboarding.coordinator.OnboardingCoordinator
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.navigation.GoToMainApp
import gr.thanflix.presentation.base.navigation.PopToRootAction
import gr.thanflix.presentation.base.navigation.PresentFeedbackAction
import gr.thanflix.presentation.components.FeedbackMessageView
import gr.thanflix.series.coordinator.SeriesAction
import gr.thanflix.series.coordinator.SeriesCoordinator
import javax.inject.Inject

class GoToAuthorization: Action
class GoToMoviesTab : Action
class GoToSeriesTab : Action

class MainAppCoordinator @Inject constructor() : Coordinator {

    override val graphId: Int = R.id.main_nav_graph

    override var context: Context? = null
    override var navController: NavController? = null

    override fun start() {
        handleAction(GoToAuthorization())
    }

    override fun handleAction(action: Action) {
        when (action) {
            is GoToAuthorization -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                val coordinator = AuthorizationCoordinator(context, navController)
                coordinator.start()
            }
            is GoToMoviesTab, GoToMainApp -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                val coordinator = MoviesCoordinator(context, navController)
                coordinator.start()
            }
            is GoToSeriesTab -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                val coordinator = SeriesCoordinator(context, navController)
                coordinator.start()
            }
            is MoviesAction -> {
                val coordinator = MoviesCoordinator(context, navController)
                coordinator.handleAction(action)
            }
            is SeriesAction -> {
                val coordinator = SeriesCoordinator(context, navController)
                coordinator.handleAction(action)
            }
            is AuthorizationAction -> {
                val coordinator = AuthorizationCoordinator(context, navController)
                coordinator.handleAction(action)
            }
            is OnboardingAction -> {
                val coordinator = OnboardingCoordinator(context, navController)
                coordinator.handleAction(action)
            }
            else -> super.handleAction(action)
        }
    }
}

