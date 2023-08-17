package gr.thanflix.presentation.base.viewModel

import android.content.Context
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.FeedbackMessage
import gr.thanflix.domain.models.base.FeedbackMessageType
import gr.thanflix.presentation.R
import gr.thanflix.presentation.base.navigation.Action
import gr.thanflix.presentation.base.navigation.BaseActionDispatcher
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import timber.log.Timber

interface BaseErrorDispatcher {
    fun handleErrors(
        actionHandler: BaseActionHandler?,
        error: BaseException,
        config: HandleErrorsConfig = HandleErrorsConfig.Builder().build()
    ): Boolean

    fun testInjection()
}