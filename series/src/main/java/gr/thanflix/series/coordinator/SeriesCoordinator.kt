package gr.thanflix.series.coordinator

import android.os.Bundle
import androidx.navigation.NavController
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.series.R
import gr.thanflix.series.util.Extras

sealed class SeriesAction: Action {
    object GoToSeriesLanding: SeriesAction()
    class GoToSeriesDetails(val id: Int): SeriesAction()
}

class SeriesCoordinator (
    override var navController: NavController
): Coordinator {
    override val graphId: Int = R.id.series_nav_graph

    override fun start() {
        navigate(graphId)
    }

    override fun handleAction(action: Action) {
        when(action) {
            is SeriesAction.GoToSeriesLanding -> navigate(R.id.seriesLandingFragment)

            is SeriesAction.GoToSeriesDetails -> {
                val args = Bundle()
                args.putInt(Extras.ARG_SERIES_ID, action.id)

                navigate(R.id.seriesDetailsFragment, args)
            }
        }
    }
}