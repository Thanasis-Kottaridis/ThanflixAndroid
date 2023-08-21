package gr.thanflix.series.ui.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.base.ui.compose.ComposeBaseFragment
import gr.thanflix.presentation.base.ui.compose.theme.ThanflixAndroidTheme
import gr.thanflix.presentation.utils.helpers.viewBinding
import gr.thanflix.series.R
import gr.thanflix.series.databinding.FragmentSeriesLandingBinding

@AndroidEntryPoint
class SeriesLandingFragment : ComposeBaseFragment(R.layout.fragment_series_landing) {

    private val binding by viewBinding(FragmentSeriesLandingBinding::bind)
    private val viewModel: SeriesLandingViewModel by viewModels()

    override fun setUpObservers() {
//        TODO("Not yet implemented")
    }

    override fun populateData() {
//        TODO("Not yet implemented")
    }


    /**
     * Composable Content
     */
    @Composable
    override fun ComposableContent() {
        Column {
            Text(text = "Hello, Compose!")
            Text(text = "Hello, Compose!")
            Text(text = "Hello, Compose!")
        }
    }
}