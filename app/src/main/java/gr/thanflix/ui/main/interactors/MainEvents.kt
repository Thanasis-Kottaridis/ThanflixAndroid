package gr.thanflix.ui.main.interactors

import androidx.annotation.IdRes

sealed class MainEvents {
    object SelectMoviesTab: MainEvents()
    object SelectSeriesTab: MainEvents()

}