package gr.thanflix.series.ui.landing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gr.thanflix.domain.models.show.Show

@Composable
fun SeriesCell(
    show: Show,
    onSelectItem: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .size(120.dp, 180.dp)
            .padding(8.dp)
            .background(Color.Gray, shape = RoundedCornerShape(16.dp))
            .clickable { onSelectItem.invoke(show.id ?: -1) }
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