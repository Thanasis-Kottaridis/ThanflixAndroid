package gr.thanflix.movies.ui.landing

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