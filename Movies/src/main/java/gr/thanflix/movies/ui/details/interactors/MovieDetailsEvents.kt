package gr.thanflix.movies.ui.details.interactors

sealed class MovieDetailsEvents {
    class SetMovieId(val id: Int): MovieDetailsEvents()
    object GoBack : MovieDetailsEvents()
    object FetchData : MovieDetailsEvents()
}
