package gr.thanflix.coordinator

import android.graphics.Movie
import androidx.navigation.NavController
import gr.thanflix.R
import gr.thanflix.movies.coordinator.MoviesAction
import gr.thanflix.movies.coordinator.MoviesCoordinator
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.navigation.PopToRootAction
import gr.thanflix.series.coordinator.SeriesAction
import gr.thanflix.series.coordinator.SeriesCoordinator
import javax.inject.Inject

class GoToMoviesTab : Action
class GoToSeriesTab : Action

class MainAppCoordinator @Inject constructor() : Coordinator {

    override val graphId: Int = R.id.main_nav_graph

    override lateinit var navController: NavController

    override fun start() {
        handleAction(GoToMoviesTab())
    }

    override fun handleAction(action: Action) {
        when (action) {
            is GoToMoviesTab -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                val coordinator = MoviesCoordinator(navController)
                coordinator.start()
            }
            is GoToSeriesTab -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                val coordinator = SeriesCoordinator(navController)
                coordinator.start()
            }
            is MoviesAction -> {
                val coordinator = MoviesCoordinator(navController)
                coordinator.handleAction(action)
            }
            is SeriesAction -> {
                val coordinator = SeriesCoordinator(navController)
                coordinator.handleAction(action)
            }
            else -> super.handleAction(action)
        }
    }
}

