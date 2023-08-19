package gr.thanflix.series.coordinator

import androidx.navigation.NavController
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.navigation.PopToRootAction
import gr.thanflix.series.R

sealed class SeriesAction: Action {
    object GoToSeriesLanding: SeriesAction()
    class GoToSeriesDetails(id: Int): SeriesAction()

    object GoToTest: SeriesAction()
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
            is SeriesAction.GoToTest -> navigate(R.id.test_nav_graph)
            else ->  super.handleAction(action)
        }
    }
}