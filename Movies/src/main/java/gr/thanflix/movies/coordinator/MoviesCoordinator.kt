package gr.thanflix.movies.coordinator

import android.os.Bundle
import androidx.navigation.NavController
import gr.thanflix.movies.R
import gr.thanflix.movies.util.Extras
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.Coordinator

sealed class MoviesAction : Action {
    object GoToMoviesLanding : MoviesAction()
    class GoToMovieDetails(val id: Int) : MoviesAction()
}

class MoviesCoordinator(
    override var navController: NavController
) : Coordinator {

    override val graphId: Int = R.id.movies_nav_graph
    override fun start() {
        navigate(graphId)
    }

    override fun handleAction(action: Action) {
        when (action) {
            is MoviesAction.GoToMoviesLanding -> navigate(R.id.moviesLandingFragment)
            is MoviesAction.GoToMovieDetails -> {
                val args = Bundle()
                args.putInt(Extras.ARG_MOVIE_ID, action.id)

                navigate(R.id.moviesDetailsFragment, args)
            }
        }
    }
}