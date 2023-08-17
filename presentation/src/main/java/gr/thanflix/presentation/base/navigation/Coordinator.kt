package gr.thanflix.presentation.base.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph

interface Coordinator: BaseActionHandler {

    var navController: NavController

    /**
     * Navigate at inner graph with gid and start destination id
     */
    fun navigate(@IdRes graphId: Int, @IdRes resID: Int, args: Bundle?) {
        val graph = navController.graph.findNode(graphId) as NavGraph
        graph.setStartDestination(resID)
        navController.navigate(graphId, args)
    }

    override fun handleAction(action: Action) {
        when (action) {
            is Action.PopAction -> navController.popBackStack()
            is Action.PopToDestination -> {
                navController.popBackStack(action.destinationId, action.inclusive)
            }
            is Action.PopToRootAction -> {}
            is Action.ShowLoaderAction -> {}
            is Action.HideLoaderAction -> {}
            is Action.PresentFeedbackAction -> {}
        }
    }
}