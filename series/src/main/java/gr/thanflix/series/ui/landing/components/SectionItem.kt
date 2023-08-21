package gr.thanflix.series.ui.landing.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.thanflix.domain.models.base.SectionModel
import gr.thanflix.domain.models.show.Show

@Composable
fun SeriesSection(
    section: SectionModel<Int, Show>,
    onSelectItem: (Int) -> Unit
) {
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
                SeriesCell(show, onSelectItem)

            }
        }
    }
}