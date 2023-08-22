package gr.thanflix.movies.ui.landing

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.movies.R
import gr.thanflix.movies.databinding.FragmentMoviesLandingBinding
import gr.thanflix.movies.ui.landing.components.MoviesSectionAdapter
import gr.thanflix.movies.ui.landing.interactors.MoviesLandingEvents
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesLandingFragment : BaseFragment(R.layout.fragment_movies_landing) {

    private val binding by viewBinding(FragmentMoviesLandingBinding::bind)
    private val viewModel: MoviesLandingViewModel by viewModels()

    private lateinit var moviesParentAdapter: MoviesSectionAdapter

    override fun setupView() {
        binding.moviesParentRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        moviesParentAdapter = MoviesSectionAdapter(
            moviesSections = listOf(),
            onItemClick = { id -> viewModel.onTriggerEvent(MoviesLandingEvents.SelectMovie(id)) })
        binding.moviesParentRecyclerView.adapter = moviesParentAdapter
    }

    override fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                moviesParentAdapter.addData(it.moviesDisplayable)
            }
        }

    }

    override fun populateData() {
        viewModel.onTriggerEvent(MoviesLandingEvents.FetchData)
    }
}