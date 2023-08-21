package gr.thanflix.series.ui.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.presentation.base.ui.compose.ComposeBaseFragment
import gr.thanflix.series.R
import gr.thanflix.series.ui.details.components.SeriesDetailsScreen
import gr.thanflix.series.ui.details.interactors.SeriesDetailsEvents
import gr.thanflix.series.util.Extras

@AndroidEntryPoint
class SeriesDetailsFragment: ComposeBaseFragment(R.layout.fragment_series_details) {

    private val viewModel: SeriesDetailsViewModel by viewModels()

    override fun initArgs() {
        super.initArgs()

        arguments?.getInt(Extras.ARG_SERIES_ID)?.let { id ->
            viewModel.onTriggerEvent(SeriesDetailsEvents.SetSeriesId(id))
        }
    }

    override fun populateData() {
        viewModel.onTriggerEvent(SeriesDetailsEvents.FetchData)
    }

    @Composable
    override fun ComposableContent() {

        val state by viewModel.state.collectAsState()

        SeriesDetailsScreen(
            state = state,
            onTriggerEvent = viewModel::onTriggerEvent
        )
    }
}