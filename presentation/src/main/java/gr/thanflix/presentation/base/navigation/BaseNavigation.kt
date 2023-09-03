package gr.thanflix.presentation.base.navigation

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import gr.thanflix.domain.models.base.FeedbackMessage

// MARK: Navigation Actions
/// # Action
///  All navigation actions must extend this in order to be handled from Actions Handlers.
interface Action {}

object PopAction: Action
class PopToDestination(@IdRes val destinationId: Int, val inclusive: Boolean = true): Action
object PopToRootAction: Action
class ClearGraphAction(val graphId: Int): Action
object ShowLoaderAction: Action
object HideLoaderAction: Action
class PresentFeedbackAction(val feedbackMessage: FeedbackMessage) : Action

interface BaseActionHandler {
    fun handleAction(action: Action)
}

interface BaseActionDispatcher {
    var actionHandler: BaseActionHandler?
}



