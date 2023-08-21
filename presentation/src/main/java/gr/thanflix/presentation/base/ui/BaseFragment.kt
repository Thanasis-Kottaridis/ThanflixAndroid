package gr.thanflix.presentation.base.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import gr.thanflix.presentation.utils.helpers.BottomNavBarHandler
import gr.thanflix.presentation.utils.helpers.BottomNavBarItem

abstract class BaseFragment(
    layoutResId: Int
) : Fragment(layoutResId) {

    /**
     * Variables to override in order to change the load behavior or time
     */
    protected open var shouldReLoadDataOnBack = false

    /**
     *     /// this helper var is used to hide nav bar when push controller
     */

    /**
     * Set to true to show the bottom nav bar in a fragment e.g. movies landing fragment
     */
    protected open var hideNavigationBar = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initArgs()
        setupView()
        setUpObservers()
        populateData()
    }

    /**
     *  Base Fragment Template functions
     */
    open fun initArgs() {} // optional implementation in case we have arguments to pass in this fragment

    abstract fun setupView()
    abstract fun setUpObservers()
    abstract fun populateData()

    /**
     * Helper Functions
     */

    fun setBottomNavBarSelectedItem(item: BottomNavBarItem) {
        if (activity is BottomNavBarHandler)
            (activity as BottomNavBarHandler).setBottomNavBarSelectedItem(item)
    }
}