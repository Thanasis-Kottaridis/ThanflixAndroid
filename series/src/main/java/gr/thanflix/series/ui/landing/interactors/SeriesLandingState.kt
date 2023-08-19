package gr.thanflix.series.ui.landing.interactors

import gr.thanflix.presentation.base.viewModel.BaseState

data class SeriesLandingState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true
): BaseState()
