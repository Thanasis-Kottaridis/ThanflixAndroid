package gr.thanflix.presentation.base.navigation

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import gr.thanflix.domain.models.base.FeedbackMessage
import gr.thanflix.presentation.components.FeedbackMessageView

interface Coordinator : BaseActionHandler {

    /**
     * Activity Context:
     * Context for the activity bounded with this coordinator
     * needed to in order to display view and navigate to other Activities
     */
    var context: Context?

    /**
     * Const Val that contains Navigation graph ID.
     */
    val graphId: Int

    /**
     * Navigation Controller responsible for handling Coordinator navigation
     */
    var navController: NavController?

    /**
     * Start function
     * This function is used in order to properly
     * initialize coordinator and navigation graph.
     */
    fun start()

    /**
     * Stop function
     * This function is used in order to reset Coordinator
     * and clear navController and context reference
     */
    fun stop() {
        context = null
        navController = null
    }

    /**
     * Navigate with res id
     */
    fun navigate(resId: Int, args: Bundle? = null) {
        navController?.navigate(resId, args, null)
    }

    /**
     * Navigate at inner graph with gid and start destination id
     */
    fun navigate(graphId: Int, resID: Int, args: Bundle? = null) {
        val graph = navController?.graph?.findNode(graphId) as NavGraph
        graph.setStartDestination(resID)
        navController?.navigate(graphId, args)
    }

    override fun handleAction(action: Action) {
        when (action) {
            is PopAction -> navController?.popBackStack()
            is PopToDestination -> navController?.popBackStack(
                action.destinationId,
                action.inclusive
            )

            is PopToRootAction -> navController?.popBackStack(graphId, true)
            is ClearGraphAction -> navController?.popBackStack(action.graphId, true)
            is ShowLoaderAction -> {}
            is HideLoaderAction -> {}
            is PresentFeedbackAction -> {
                FeedbackMessageView.build(
                    type = action.feedbackMessage.type,
                    message = action.feedbackMessage.message,
                    context = context ?: return
                ).show()
            }
        }
    }
}