package gr.thanflix.presentation.base.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph

interface Coordinator: BaseActionHandler {

    /**
     * Const Val that contains Navigation graph ID.
     */
    val graphId: Int

    /**
     * Navigation Controller responsible for handling Coordinator navigation
     */
    var navController: NavController

    /**
     * Start function
     * This function is used in order to properly
     * initialize coordinator and navigation graph.
     */
    fun start()

    /**
     * Navigate with res id
     */
    fun navigate(resId: Int, args: Bundle? = null) {
        navController.navigate(resId, args, null)
    }

    /**
     * Navigate at inner graph with gid and start destination id
     */
    fun navigate(graphId: Int, resID: Int, args: Bundle? = null) {
        val graph = navController.graph.findNode(graphId) as NavGraph
        graph.setStartDestination(resID)
        navController.navigate(graphId, args)
    }

    override fun handleAction(action: Action) {
        when (action) {
            is PopAction -> navController.popBackStack()
            is PopToDestination -> navController.popBackStack(action.destinationId, action.inclusive)
            is PopToRootAction -> navController.popBackStack(graphId, true)
            is ClearGraphAction ->  navController.popBackStack(action.graphId, true)
            is ShowLoaderAction -> {}
            is HideLoaderAction -> {}
            is PresentFeedbackAction -> {}
        }
    }
}