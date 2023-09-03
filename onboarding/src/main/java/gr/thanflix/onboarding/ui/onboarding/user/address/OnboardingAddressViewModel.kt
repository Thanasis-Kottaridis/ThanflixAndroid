package gr.thanflix.onboarding.ui.onboarding.user.address

import android.content.Context
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.di.IoDispatcher
import gr.thanflix.domain.models.base.BaseException
import gr.thanflix.domain.models.base.Result
import gr.thanflix.onboarding.ui.onboarding.user.address.interactors.OnboardingAddressEvents
import gr.thanflix.onboarding.ui.onboarding.user.address.interactors.OnboardingAddressState
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
class OnboardingAddressViewModel @Inject constructor(
    @ApplicationContext context: Context,
    baseErrorDispatcher: BaseErrorDispatcher,
    override var actionHandler: BaseActionHandler?,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : BaseViewModel<OnboardingAddressState, OnboardingAddressEvents>(context, baseErrorDispatcher) {

    private var mediator: OnboardingMediator? = null

    override var mState: MutableStateFlow<OnboardingAddressState> =
        MutableStateFlow(OnboardingAddressState())
    override val state: StateFlow<OnboardingAddressState>
        get() = mState.asStateFlow()

    init {
        commonInit()
    }

    override fun onTriggerEvent(event: OnboardingAddressEvents) {
        when (event) {
            is OnboardingAddressEvents.NextStep -> setUserAddress(event.streetName, event.streetNumber, event.postalCode)

        }
    }

    fun injectMediator(mediator: OnboardingMediator) {
        this.mediator = mediator
    }

    private fun setUserAddress(streetName: String, streetNumber: String,  postalCode: String) {
        when (val result = mediator?.setAddressDetails(streetName, streetNumber, postalCode) ?: return) {
            is Result.Success -> {}
            is Result.Failure -> handleErrors(result.errorBody)
        }
    }
}