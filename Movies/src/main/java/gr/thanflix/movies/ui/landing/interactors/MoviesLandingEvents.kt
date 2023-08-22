package gr.thanflix.movies.ui.landing.interactors

sealed class MoviesLandingEvents {
    object FetchData: MoviesLandingEvents()
    class SelectMovie(val id: Int): MoviesLandingEvents()

}