package gr.thanflix

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gr.thanflix.data.utils.DataTester
import gr.thanflix.domain.utils.ModuleTester
import gr.thanflix.movies.TestMovies
import gr.thanflix.presentation.utils.PresentationTester
import gr.thanflix.series.util.SeriesTester
import gr.thanflix.ui.theme.ThanflixAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThanflixAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Divider()

        Text(
            text = "Test Domain",
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Text(
            text = ModuleTester.testModule(),
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Divider()

        Text(
            text = "Test Data",
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Text(
            text = DataTester.testModule(),
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Divider()

        Text(
            text = "Test Presentation",
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Text(
            text = PresentationTester.testModule(),
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Divider()

        Text(
            text = "Test Movies",
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Text(
            text = TestMovies.testModule(),
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Divider()

        Text(
            text = "Test Series",
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )

        Text(
            text = SeriesTester.testModule(),
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThanflixAndroidTheme {
        Greeting("Android")
    }
}