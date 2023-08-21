package gr.thanflix.series.ui.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import gr.thanflix.series.ui.details.interactors.SeriesDetailsEvents
import gr.thanflix.series.ui.details.interactors.SeriesDetailsState

@Composable
fun SeriesDetailsScreen(
    state: SeriesDetailsState,
    onTriggerEvent: (SeriesDetailsEvents) -> Unit
) {

    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            ShowInfoView(state)
        }

        item {
            ShowOverviewView(state)
        }
    }
}