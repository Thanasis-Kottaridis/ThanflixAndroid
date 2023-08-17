package gr.thanflix.base

import android.content.Context
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.FeedbackMessage
import gr.thanflix.domain.models.base.FeedbackMessageType
import gr.thanflix.presentation.R
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.HandleErrorsConfig
import timber.log.Timber


class BaseErrorDispatcherImpl(
    private val context: Context
): BaseErrorDispatcher {
    override fun handleErrors(
        actionHandler: BaseActionHandler?,
        error: BaseException,
        config: HandleErrorsConfig
    ): Boolean {
        val genericErrorMessage = config.genericErrorMessage

        // Handle Unauthorized
        if (error.errorCode == 401) {

            // Custom handling
            config.handleUnauthorized?.invoke() ?: run {
                logUserOut(actionHandler, true)
            }
            return true
        }
        // Handle Unenrolled
        else if (error.errorCode == 403) {

            // Custom handling
            config.handleUnenroll?.invoke() ?: run {
                logUserOut(actionHandler, true)
            }
            return true
        }
        // Handle internal server error
        else if (error.errorCode == 500) {

            // Custom handling
            config.handleInternalServerError?.invoke() ?: run {
                val action = Action.PresentFeedbackAction(
                    feedbackMessage = FeedbackMessage(genericErrorMessage, FeedbackMessageType.Error)
                )
                actionHandler?.handleAction(action)
            }
            return true
        }
        // Default error handling.
        else {

            // Custom handling
            config.defaultHandling?.invoke() ?: run {
                val action = Action.PresentFeedbackAction(
                    feedbackMessage = FeedbackMessage(genericErrorMessage, FeedbackMessageType.Error)
                )
                actionHandler?.handleAction(action)
            }
            return true
        }
    }

    override fun testInjection() {
        Timber.d("BaseErrorDispatcher Debug... Dependency Injection WORKS!!!!!!")
    }

    private fun logUserOut(
        actionHandler: BaseActionHandler?,
        isHard: Boolean = false,
        forReason: String? = null
    ) {
        val defaultMessage = context.resources.getString(R.string.login_session_invalidated)
        // Show feedbackMessage
        val action = Action.PresentFeedbackAction(
            feedbackMessage = FeedbackMessage(
                forReason ?: defaultMessage,
                FeedbackMessageType.Error
            )
        )
        actionHandler?.handleAction(action)
    }
}
