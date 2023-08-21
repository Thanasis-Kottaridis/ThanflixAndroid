package gr.thanflix.series.ui.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.thanflix.domain.models.show.Overview

@Composable
fun ShowOverViewCell(overview: Overview, showDivider: Boolean) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = overview.key,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            color = Color.Gray
        )

        Text(
            text = overview.value ?: "NO_VALUE",
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color.Black
        )

        if (showDivider)
            Divider(Modifier.background(Color.Gray))
    }
}