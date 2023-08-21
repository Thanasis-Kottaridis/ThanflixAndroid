package gr.thanflix.series.ui.landing.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gr.thanflix.series.ui.landing.interactors.SeriesLandingEvents
import gr.thanflix.series.ui.landing.interactors.SeriesLandingState

@Composable
fun SeriesLandingScreen(
    state: SeriesLandingState,
    onTriggerEvent: (SeriesLandingEvents) -> Unit
) {
    // MainContent
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
    ) {
        items(state.seriesDisplayable) {
            SeriesSection(
                section = it,
                onSelectItem = { id -> onTriggerEvent.invoke(SeriesLandingEvents.SelectSeries(id)) }
            )
        }
    }
}

