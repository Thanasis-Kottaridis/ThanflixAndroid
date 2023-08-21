package gr.thanflix.series.ui.landing.interactors

import gr.thanflix.domain.models.base.SectionModel
import gr.thanflix.domain.models.show.Show
import gr.thanflix.presentation.base.viewModel.BaseState
import gr.thanflix.presentation.R

data class SeriesLandingState(
    override val isLoading: Boolean = false,
    override val hasInternet: Boolean = true,
    val todaySeries: List<Show> = emptyList(),
    val onTheAirSeries: List<Show> = emptyList(),
    val popularSeries: List<Show> = emptyList(),
    val topRatedSeries: List<Show> = emptyList()
): BaseState(){
    val seriesDisplayable: List<SectionModel<Int, Show>>
        get() {
            val sections = mutableListOf<SectionModel<Int, Show>>()

            if (todaySeries.isNotEmpty()) {
                val section = SectionModel(
                    model = R.string.SERIES_LANDING_TODAY,
                    items = todaySeries
                )
                sections.add(section)
            }

            if (onTheAirSeries.isNotEmpty()) {
                val section = SectionModel(
                    model =  R.string.SERIES_LANDING_ON_THE_AIR,
                    items = onTheAirSeries
                )
                sections.add(section)
            }

            if (popularSeries.isNotEmpty()) {
                val section = SectionModel(
                    model = R.string.SERIES_LANDING_POPULAR,
                    items = popularSeries
                )
                sections.add(section)
            }

            if (topRatedSeries.isNotEmpty()) {
                val section = SectionModel(
                    model = R.string.SERIES_LANDING_TOP_RATED,
                    items = topRatedSeries
                )
                sections.add(section)
            }

            return sections
        }
}
