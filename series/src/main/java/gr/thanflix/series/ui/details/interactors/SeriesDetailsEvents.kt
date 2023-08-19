package gr.thanflix.series.ui.details.interactors

sealed class SeriesDetailsEvents {
    object GoBack: SeriesDetailsEvents()
    object GoToTest: SeriesDetailsEvents()
}