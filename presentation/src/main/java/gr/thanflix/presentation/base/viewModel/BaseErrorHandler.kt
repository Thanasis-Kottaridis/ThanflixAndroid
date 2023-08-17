package gr.thanflix.presentation.base.viewModel

import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.Result

interface BaseErrorHandler {
    /**
    Handle Errors methos takes a BaseException error and displays the respective message / view to user.
    - Parameter error: Base excatpion error.
    - Parameter config: This parameter is used to pass error handling configurations
    - Returns: A Bool that shows if the error case handled or not.
     */
    fun handleErrors(
        error: BaseException,
        config: HandleErrorsConfig = HandleErrorsConfig.Builder().build()
    ): Boolean

}

class HandleErrorsConfig(
    val genericErrorMessage: String,
    val handleForceUpdate: (() -> Unit)?,
    val handleMaintenance: (() -> Unit)?,
    val handleUnauthorized: (() -> Unit)?,
    val handleUnenroll: (() -> Unit)?,
    val handlePassExpiration: (() -> Unit)?,
    val handleInternalServerError: (() -> Unit)?,
    val defaultHandling: (() -> Unit)?
) {
    class Builder {
        private var genericErrorMessage = "Something went wrong. Please try again."
        private var handleForceUpdate: (() -> Unit)? = null
        private var handleMaintenance: (() -> Unit)? = null
        private var handleUnauthorized: (() -> Unit)? = null
        private var handleUnenroll: (() -> Unit)? = null
        private var handlePassExpiration: (() -> Unit)? = null
        private var handleInternalServerError: (() -> Unit)? = null
        private var defaultHandling: (() -> Unit)? = null

        fun setGenericErrorMessage(message: String): Builder {
            genericErrorMessage = message
            return this
        }

        fun setHandleForceUpdate(handler: (() -> Unit)?): Builder {
            handleForceUpdate = handler
            return this
        }

        fun setHandleMaintenance(handler: (() -> Unit)?): Builder {
            handleMaintenance = handler
            return this
        }

        fun setHandleUnauthorized(handler: (() -> Unit)?): Builder {
            handleUnauthorized = handler
            return this
        }

        fun setHandleUnenroll(handler: (() -> Unit)?): Builder {
            handleUnenroll = handler
            return this
        }

        fun setPassExpiration(handler: (() -> Unit)?): Builder {
            handlePassExpiration = handler
            return this
        }

        fun setHandleInternalServerError(handler: (() -> Unit)?): Builder {
            handleInternalServerError = handler
            return this
        }

        fun setDefaultHandling(handler: (() -> Unit)?): Builder {
            defaultHandling = handler
            return this
        }

        fun build(): HandleErrorsConfig {
            return HandleErrorsConfig(
                genericErrorMessage = genericErrorMessage,
                handleForceUpdate = handleForceUpdate,
                handleMaintenance = handleMaintenance,
                handleUnauthorized = handleUnauthorized,
                handleUnenroll = handleUnenroll,
                handlePassExpiration = handlePassExpiration,
                handleInternalServerError = handleInternalServerError,
                defaultHandling = defaultHandling
            )
        }
    }
}
