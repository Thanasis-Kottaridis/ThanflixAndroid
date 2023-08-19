package gr.thanflix.ui.main.interactors

import gr.thanflix.presentation.base.viewModel.BaseState

data class MainState(
    override val isLoading: Boolean = false,
    override var hasInternet: Boolean = true
): BaseState()
