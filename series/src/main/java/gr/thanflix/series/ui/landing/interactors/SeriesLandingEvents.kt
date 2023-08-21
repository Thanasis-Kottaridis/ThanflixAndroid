package gr.thanflix.series.ui.landing.interactors

sealed class SeriesLandingEvents {
    object FetchData: SeriesLandingEvents()
    class SelectSeries(val id: Int): SeriesLandingEvents()
}
