package gr.thanflix.series.ui.details.interactors

sealed class SeriesDetailsEvents {
    class SetSeriesId(val id: Int): SeriesDetailsEvents()
    object GoBack: SeriesDetailsEvents()
    object FetchData: SeriesDetailsEvents()

}