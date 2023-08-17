package gr.thanflix.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.R
import gr.thanflix.TestEvents
import gr.thanflix.TestViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint // hilt needs at least one activity annotated with android entry point
class MainActivity : FragmentActivity() {

    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.onTriggerEvent(TestEvents.FetchPopularMovies)


        // Start observing the Flow when the activity is created
        lifecycleScope.launch {
            viewModel.state.collect {
                Toast.makeText(
                    this@MainActivity,
                    "Shows received ... ${it.popularMovies.count()}",
                    Toast.LENGTH_LONG
                ).show()

            }
        }
    }
}