package gr.thanflix.series.ui.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import gr.thanflix.series.ui.details.interactors.SeriesDetailsState

@Composable
fun ShowInfoView(state: SeriesDetailsState) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = state.details.title ?: "N0_TITLE",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Black
        )

        Text(
            text = state.details.releaseDate ?: "N0_DATE",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Gray
        )
    }
}