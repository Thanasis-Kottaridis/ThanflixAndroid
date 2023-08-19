package gr.thanflix.movies.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.movies.R
import gr.thanflix.movies.databinding.FragmentMoviesDetailsBinding
import gr.thanflix.movies.databinding.FragmentMoviesLandingBinding
import gr.thanflix.movies.ui.landing.MoviesLandingViewModel
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding

@AndroidEntryPoint
class MoviesDetailsFragment : BaseFragment(R.layout.fragment_movies_details) {

    private val binding by viewBinding(FragmentMoviesDetailsBinding::bind)
    private val viewModel: MoviesLandingViewModel by viewModels()

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