package gr.thanflix.movies.ui.landing.interactors

import gr.thanflix.domain.models.show.Show
import gr.thanflix.presentation.base.viewModel.BaseState

data class MoviesLandingState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true,
    val nowPlayingMovies: List<Show> = listOf(),
    val popularMovies: List<Show> = listOf(),
    val topRatedMovies: List<Show> = listOf(),
    val upcomingMovies: List<Show> = listOf()
): BaseState()