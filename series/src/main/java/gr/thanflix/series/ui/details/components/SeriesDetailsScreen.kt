package gr.thanflix.series.ui.details.components

import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import gr.thanflix.presentation.R
import gr.thanflix.presentation.components.ThanflixAppBar
import gr.thanflix.presentation.components.ThanflixAppBarListener
import gr.thanflix.series.ui.details.interactors.SeriesDetailsEvents
import gr.thanflix.series.ui.details.interactors.SeriesDetailsState

@Composable
fun SeriesDetailsScreen(
    state: SeriesDetailsState,
    onTriggerEvent: (SeriesDetailsEvents) -> Unit
) {
    Column {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            factory = {
                ThanflixAppBar(it, null).apply {
                    setAppBarListener(object: ThanflixAppBarListener {
                        override fun onBackButtonPressed(view: View): Boolean {
                            onTriggerEvent.invoke(SeriesDetailsEvents.GoBack)
                            return true
                        }
                    })
                }
            },
            update = {
                it.primaryTitle = state.details.title ?: "No Title"
            }
        )

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

}