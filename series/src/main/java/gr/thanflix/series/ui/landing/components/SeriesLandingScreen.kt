package gr.thanflix.series.ui.landing.components

import android.hardware.TriggerEvent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import gr.thanflix.domain.models.base.SectionModel
import gr.thanflix.domain.models.show.Show
import gr.thanflix.presentation.utils.helpers.compose.OnLifecycleEvent
import gr.thanflix.series.ui.landing.interactors.SeriesLandingEvents
import gr.thanflix.series.ui.landing.interactors.SeriesLandingState

@Composable
fun SeriesLandingScreen(
    state: SeriesLandingState,
    onTriggerEvent: (SeriesLandingEvents) -> Unit
) {

    // Add State observer:
    OnLifecycleEvent { owner, event ->
        // do stuff on event
        when (event) {
            Lifecycle.Event.ON_CREATE -> {
                onTriggerEvent(SeriesLandingEvents.FetchData)
            }
            else -> { /* other stuff */ }
        }
    }

    // MainContent
    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
    ) {
        items(state.seriesDisplayable) {
            SectionItem(section = it)
        }
    }
}

@Composable
fun SectionItem(section: SectionModel<Int, Show>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(section.model),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )

        LazyRow {
            items(section.items) { show ->
                SeriesCell(show)
            }
        }
    }
}

@Composable
fun SeriesCell(show: Show) {
    Box(
        modifier = Modifier
            .size(120.dp, 180.dp)
            .padding(8.dp)
            .background(Color.Gray, shape = RoundedCornerShape(16.dp)) // Set corner radius to 16dp
    ) {
        Text(
            text = show.title ?: "NO_TITLE", // Replace with actual title
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp),
            color = Color.White // Adjust text color as needed
        )
    }
}