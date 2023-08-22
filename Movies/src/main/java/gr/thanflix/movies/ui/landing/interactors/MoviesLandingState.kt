package gr.thanflix.movies.ui.landing.interactors

import gr.thanflix.domain.models.base.SectionModel
import gr.thanflix.domain.models.show.Show
import gr.thanflix.presentation.R
import gr.thanflix.presentation.base.viewModel.BaseState

data class MoviesLandingState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true,
    val nowPlayingMovies: List<Show> = listOf(),
    val popularMovies: List<Show> = listOf(),
    val topRatedMovies: List<Show> = listOf(),
    val upcomingMovies: List<Show> = listOf()
): BaseState() {
    val moviesDisplayable: List<SectionModel<Int, Show>>
        get() {
            val sections = mutableListOf<SectionModel<Int, Show>>()

            if (nowPlayingMovies.isNotEmpty()) {
                val section = SectionModel(
                    model = R.string.MOVIES_LANDING_NOW_PLAYING,
                    items = nowPlayingMovies
                )
                sections.add(section)
            }

            if (popularMovies.isNotEmpty()) {
                val section = SectionModel(
                    model =  R.string.MOVIES_LANDING_MOST_POPULAR,
                    items = popularMovies
                )
                sections.add(section)
            }

            if (topRatedMovies.isNotEmpty()) {
                val section = SectionModel(
                    model = R.string.MOVIES_LANDING_TOP_RATED,
                    items = topRatedMovies
                )
                sections.add(section)
            }

            if (upcomingMovies.isNotEmpty()) {
                val section = SectionModel(
                    model = R.string.MOVIES_LANDING_UPCOMING,
                    items = upcomingMovies
                )
                sections.add(section)
            }

            return sections
        }
}