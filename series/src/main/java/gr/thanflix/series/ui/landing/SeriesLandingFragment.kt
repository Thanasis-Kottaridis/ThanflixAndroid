package gr.thanflix.series.ui.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.base.ui.compose.ComposeBaseFragment
import gr.thanflix.presentation.base.ui.compose.theme.ThanflixAndroidTheme
import gr.thanflix.presentation.utils.helpers.viewBinding
import gr.thanflix.series.R
import gr.thanflix.series.databinding.FragmentSeriesLandingBinding
import gr.thanflix.series.ui.landing.components.SeriesLandingScreen
import gr.thanflix.series.ui.landing.interactors.SeriesLandingEvents

@AndroidEntryPoint
class SeriesLandingFragment : ComposeBaseFragment(R.layout.fragment_series_landing) {

    private val viewModel: SeriesLandingViewModel by viewModels()

    override fun populateData() {
        viewModel.onTriggerEvent(SeriesLandingEvents.FetchData)
    }

    /**
     * Composable Content
     */
    @Composable
    override fun ComposableContent() {
        val state by viewModel.state.collectAsState()

        SeriesLandingScreen(
            state = state,
            onTriggerEvent = viewModel::onTriggerEvent
        )
    }
}