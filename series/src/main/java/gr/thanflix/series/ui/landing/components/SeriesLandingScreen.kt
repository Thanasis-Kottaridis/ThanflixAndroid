package gr.thanflix.series.ui.landing.components

import android.view.View
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import gr.thanflix.presentation.components.ThanflixAppBar
import gr.thanflix.presentation.R
import gr.thanflix.presentation.components.ThanflixAppBarListener
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
        item {
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp),
                factory = {
                    ThanflixAppBar(it, null).apply {
                        primaryTitle = it.getString(R.string.SERIES_LANDING_TITLE)
                        foregroundColor = R.color.tint_primary
                        noNavAction = true
                    }
                }
            )
        }

        items(state.seriesDisplayable) {
            SeriesSection(
                section = it,
                onSelectItem = { id -> onTriggerEvent.invoke(SeriesLandingEvents.SelectSeries(id)) }
            )
        }
    }
}

