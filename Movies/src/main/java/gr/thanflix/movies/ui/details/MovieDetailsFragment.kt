package gr.thanflix.movies.ui.details

import android.system.Os.bind
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.movies.R
import gr.thanflix.movies.databinding.FragmentMoviesDetailsBinding
import gr.thanflix.movies.ui.details.components.MovieOverviewAdapter
import gr.thanflix.movies.ui.details.interactors.MovieDetailsEvents
import gr.thanflix.movies.ui.landing.MoviesLandingViewModel
import gr.thanflix.movies.ui.landing.components.MoviesSectionAdapter
import gr.thanflix.movies.ui.landing.interactors.MoviesLandingEvents
import gr.thanflix.movies.util.Extras
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.components.ThanflixAppBarListener
import gr.thanflix.presentation.utils.helpers.viewBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment(R.layout.fragment_movies_details) {

    private val binding by viewBinding(FragmentMoviesDetailsBinding::bind)
    private val viewModel: MovieDetailsViewModel by viewModels()

    private lateinit var overviewAdapter: MovieOverviewAdapter

    override fun initArgs() {
        super.initArgs()

        arguments?.getInt(Extras.ARG_MOVIE_ID)?.let { id ->
            viewModel.onTriggerEvent(MovieDetailsEvents.SetMovieId(id))
        }
    }

    override fun setupView() {
        // set up appBar
        binding.appBar.setAppBarListener(object: ThanflixAppBarListener {
            override fun onBackButtonPressed(view: View): Boolean {
                viewModel.onTriggerEvent(MovieDetailsEvents.GoBack)
                return true
            }
        })

        // Set up recycler
        binding.overviewRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        overviewAdapter = MovieOverviewAdapter(listOf())
        binding.overviewRecyclerView.adapter = overviewAdapter
    }

    override fun setUpObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                it.details.apply {
                    // SET UP HEADER
                    binding.appBar.primaryTitle = title ?: "No title"

                    // SET UP MOVIE INFO
                    binding.movieTitleTextView.text = title
                    binding.releaseDateTextView.text = releaseDate

                    // SET UP OVERVIEW CONTENT
                    overviewAdapter.addData(overview ?: listOf())
                }
            }
        }
    }

    override fun populateData() {
        viewModel.onTriggerEvent(MovieDetailsEvents.FetchData)
    }
}