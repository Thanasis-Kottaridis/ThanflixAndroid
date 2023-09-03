package gr.thanflix.onboarding.ui.onboarding.user.details

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.Result
import gr.thanflix.onboarding.ui.onboarding.user.details.interactors.OnboardingDetailsEvents
import gr.thanflix.onboarding.ui.onboarding.user.details.interactors.OnboardingDetailsState
import gr.thanflix.onboarding.ui.onboarding.user.mediators.OnboardingMediator
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import gr.thanflix.presentation.base.viewModel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingDetailsViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<OnboardingDetailsState, OnboardingDetailsEvents>(context, baseErrorDispatcher) {

    private var mediator: OnboardingMediator? = null

    override var mState: MutableStateFlow<OnboardingDetailsState> =
        MutableStateFlow(OnboardingDetailsState())
    override val state: StateFlow<OnboardingDetailsState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    fun injectMediator(mediator: OnboardingMediator) {
        this.mediator = mediator
    }

    override fun onTriggerEvent(event: OnboardingDetailsEvents) {
        when (event) {
            is OnboardingDetailsEvents.NextStep -> setUserDetails(event.userName, event.userSurname, event.phoneNumber)
        }
    }

    private fun setUserDetails(userName: String, userSurname: String,  phoneNumber: String) {
        when (val result = mediator?.setUserDetails(userName, userSurname, phoneNumber) ?: return) {
            is Result.Success -> {}
            is Result.Failure -> handleErrors(result.errorBody)
        }
    }
}