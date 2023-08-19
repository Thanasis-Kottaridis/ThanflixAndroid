package gr.thanflix.movies.ui.landing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.movies.R
import gr.thanflix.movies.databinding.FragmentMoviesLandingBinding
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding

@AndroidEntryPoint
class MoviesLandingFragment : BaseFragment(R.layout.fragment_movies_landing) {

    private val binding by viewBinding(FragmentMoviesLandingBinding::bind)
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