package gr.thanflix.series.ui.details.interactors

import gr.thanflix.domain.models.show.ShowDetails
import gr.thanflix.presentation.base.viewModel.BaseState

data class SeriesDetailsState(
    override val isLoading: Boolean = false,
    override val hasInternet: Boolean = true,
    val seriesId: Int = -1,
    val details: ShowDetails = ShowDetails()
): BaseState()