package gr.thanflix.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.R
import gr.thanflix.databinding.ActivityMainBinding
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.ui.BaseActivity
import gr.thanflix.presentation.utils.helpers.BottomNavBarHandler
import gr.thanflix.presentation.utils.helpers.BottomNavBarItem
import gr.thanflix.presentation.utils.helpers.viewBinding
import gr.thanflix.ui.main.interactors.MainEvents
import javax.inject.Inject

@AndroidEntryPoint // hilt needs at least one activity annotated with android entry point
class MainActivity : BaseActivity(), BottomNavBarHandler {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel: MainViewModel by viewModels()

    /**
     * Inject and initialize MainAppCoordinator.
     */
    @Inject
    lateinit var mainAppCoordinator: Coordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpLoaderView(binding)
        // set up views
        setUpViews()
    }

    private fun setUpViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Initialize Coordinator
        mainAppCoordinator.context = this
        mainAppCoordinator.navController = navController
        mainAppCoordinator.start()
        binding.bottomNavigation.setOnItemSelectedListener(this)
    }


    override fun setBottomNavBarVisibility(show: Boolean) {
        binding.bottomNavigation.isVisible = show
    }

    /**
     * This is called from the fragments the user navigates from bottom nav bar
     * We remove the listener and add it after because when we set the selected item
     * the listener is triggered and the fragment is added twice.
     */
    override fun setBottomNavBarSelectedItem(item: BottomNavBarItem) {
        binding.bottomNavigation.setOnItemSelectedListener(null)
        when (item) {
            BottomNavBarItem.MOVIES -> {
                binding.bottomNavigation.selectedItemId = gr.thanflix.presentation.R.id.movies_tab
            }

            BottomNavBarItem.SERIES -> {
                binding.bottomNavigation.selectedItemId = gr.thanflix.presentation.R.id.series_tab
            }
        }
        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            gr.thanflix.presentation.R.id.movies_tab -> {
                viewModel.onTriggerEvent(MainEvents.SelectMoviesTab)
                return true
            }
            gr.thanflix.presentation.R.id.series_tab-> {
                viewModel.onTriggerEvent(MainEvents.SelectSeriesTab)
                return true
            }
            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainAppCoordinator.stop()
    }
}