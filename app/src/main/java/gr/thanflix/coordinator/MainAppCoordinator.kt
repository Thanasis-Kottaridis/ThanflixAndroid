package gr.thanflix.coordinator

import androidx.navigation.NavController
import gr.thanflix.R
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.navigation.PopToRootAction

class GoToMoviesTab: Action
class GoToSeriesTab: Action

class MainAppCoordinator: Coordinator {

    override val graphId: Int = R.id.main_nav_graph

    override lateinit var navController: NavController
    override fun start() {
        handleAction(GoToMoviesTab())
    }

    override fun handleAction(action: Action) {
        when(action) {
            is GoToMoviesTab -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                navigate(gr.thanflix.movies.R.id.movies_nav_graph)
            }
            is GoToSeriesTab -> {
                // reset back stack
                handleAction(PopToRootAction)
                // select tab
                navigate(gr.thanflix.series.R.id.series_nav_graph)
            }
            else ->  super.handleAction(action)
        }
    }
}