package gr.thanflix.series.ui.details.interactors

import gr.thanflix.presentation.base.viewModel.BaseState

data class SeriesDetailsState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true
): BaseState()