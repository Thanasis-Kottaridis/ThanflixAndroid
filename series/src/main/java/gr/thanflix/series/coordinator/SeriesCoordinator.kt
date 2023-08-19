package gr.thanflix.series.coordinator

import androidx.navigation.NavController
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.navigation.PopToRootAction
import gr.thanflix.series.R

sealed class SeriesAction: Action {
    object GoToSeriesLanding: Action
    class GoToSeriesDetails(id: Int): Action
}

class SeriesCoordinator (
    override var navController: NavController
): Coordinator {
    override val graphId: Int = R.id.series_nav_graph

    override fun start() {
        navigate(R.id.series_nav_graph)
    }

    override fun handleAction(action: Action) {
        when(action) {
            is SeriesAction.GoToSeriesLanding -> navigate(R.id.seriesLandingFragment)

            is SeriesAction.GoToSeriesDetails -> {
                // TODO Pass arguments
                navigate(R.id.seriesDetailsFragment)
            }
            else ->  super.handleAction(action)
        }
    }
}