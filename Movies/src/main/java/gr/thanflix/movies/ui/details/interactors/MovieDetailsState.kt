package gr.thanflix.movies.ui.details.interactors

import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.presentation.base.viewModel.BaseState

data class MovieDetailsState(
    override val isLoading: Boolean = false,
    override val isOnline: Boolean = true,
    val movieId: Int = -1,
    val details: ShowDetails = ShowDetails()
) : BaseState()