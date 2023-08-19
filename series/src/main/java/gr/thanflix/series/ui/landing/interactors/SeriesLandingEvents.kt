package gr.thanflix.series.ui.landing.interactors

sealed class SeriesLandingEvents {
    class SelectSeries(val id: Int): SeriesLandingEvents()
}
