package gr.thanflix.series.ui.details

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding
import gr.thanflix.series.R
import gr.thanflix.series.databinding.FragmentSeriesDetailsBinding
import gr.thanflix.series.ui.details.interactors.SeriesDetailsEvents

@AndroidEntryPoint
class SeriesDetailsFragment : BaseFragment(R.layout.fragment_series_details) {

    private val binding by viewBinding(FragmentSeriesDetailsBinding::bind)
    private val viewModel: SeriesDetailsViewModel by viewModels()
    override fun setupView() {
        binding.goBackButton.setOnClickListener {
            viewModel.onTriggerEvent(SeriesDetailsEvents.GoBack)
        }
    }

    override fun setUpObservers() {
//        TODO("Not yet implemented")
    }

    override fun populateData() {
//        TODO("Not yet implemented")
    }
}