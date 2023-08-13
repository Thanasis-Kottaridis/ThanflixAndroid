package gr.thanflix.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.TestViewModel
import gr.thanflix.series.SeriesActivity


@AndroidEntryPoint // hilt needs at least one activity annotated with android entry point
class MainActivity : ComponentActivity() {

    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.fetchPopularMovies()

        startActivity(Intent(this, SeriesActivity::class.java))

//        setContent {
//            ThanflixAndroidTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
    }
}