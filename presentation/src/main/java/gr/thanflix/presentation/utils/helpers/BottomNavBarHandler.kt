package gr.thanflix.presentation.utils.helpers

import com.google.android.material.navigation.NavigationBarView

/**
 * Implement this in an activity so that the attached fragment can show or hide the bottom nav bar
 */
interface BottomNavBarHandler: NavigationBarView.OnItemSelectedListener {
    fun setBottomNavBarVisibility(show: Boolean)

    fun setBottomNavBarSelectedItem(item: BottomNavBarItem)
}

enum class BottomNavBarItem {
    MOVIES, SERIES
}