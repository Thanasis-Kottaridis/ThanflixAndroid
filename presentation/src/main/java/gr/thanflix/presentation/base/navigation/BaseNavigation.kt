package gr.thanflix.presentation.base.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import gr.thanflix.domain.models.base.FeedbackMessage

// MARK: Navigation Actions
/// # Action
///  All navigation actions must extend this in order to be handled from Actions Handlers.
//interface Action {}
sealed class Action {
    object PopAction: Action()
    class PopToDestination(@IdRes val destinationId: Int, val inclusive: Boolean = true): Action()
    object PopToRootAction: Action()
    object ShowLoaderAction: Action()
    object HideLoaderAction: Action()
    class PresentFeedbackAction(val feedbackMessage: FeedbackMessage) : Action()
}

interface BaseActionHandler {
    fun handleAction(action: Action)
}

interface BaseActionDispatcher {
    var actionHandler: BaseActionHandler?
}



