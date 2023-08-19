package gr.thanflix.series.ui.details

import androidx.fragment.app.viewModels
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding
import gr.thanflix.series.R
import gr.thanflix.series.databinding.FragmentSeriesDetailsBinding

class SeriesDetailsFragment : BaseFragment(R.layout.fragment_series_details) {

    private val binding by viewBinding(FragmentSeriesDetailsBinding::bind)
    private val viewModel: SeriesDetailsViewModel by viewModels()
    override fun setupView() {
        TODO("Not yet implemented")
    }

    override fun setUpObservers() {
        TODO("Not yet implemented")
    }

    override fun populateData() {
        TODO("Not yet implemented")
    }
}