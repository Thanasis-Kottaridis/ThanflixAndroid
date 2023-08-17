package gr.thanflix.presentation.base.ui

/**
 * Implement this in an activity so that the attached fragment can show or hide the bottom nav bar
 */
interface BottomNavBarHandler {
    fun setBottomNavBarVisibility(show: Boolean, isFabOpen: Boolean)

    fun setBottomNavBarSelectedItem(item: BottomNavBarItem)
}

enum class BottomNavBarItem {
    MOVIES, SERIES
}