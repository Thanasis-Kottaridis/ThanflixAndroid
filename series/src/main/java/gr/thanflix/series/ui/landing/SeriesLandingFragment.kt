package gr.thanflix.series.ui.landing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding
import gr.thanflix.series.R
import gr.thanflix.series.databinding.FragmentSeriesDetailsBinding
import gr.thanflix.series.databinding.FragmentSeriesLandingBinding
import gr.thanflix.series.ui.details.SeriesDetailsViewModel

class SeriesLandingFragment : BaseFragment(R.layout.fragment_series_landing) {

    private val binding by viewBinding(FragmentSeriesLandingBinding::bind)
    private val viewModel: SeriesLandingViewModel by viewModels()
    override fun setupView() {
//        TODO("Not yet implemented")
    }

    override fun setUpObservers() {
//        TODO("Not yet implemented")
    }

    override fun populateData() {
//        TODO("Not yet implemented")
    }
}