package gr.thanflix.series.ui.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.thanflix.presentation.R
import gr.thanflix.series.ui.details.interactors.SeriesDetailsState

@Composable
fun ShowOverviewView(state: SeriesDetailsState) {

    Column {
        Text(
            text = stringResource(id = R.string.OVERVIEW_TITLE),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Black
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp))
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.details.overview?.forEachIndexed { index, it ->
                ShowOverViewCell(
                    it,
                    (index < (state.details.overview?.size ?: -1) - 1)
                )
            }
        }
    }
}