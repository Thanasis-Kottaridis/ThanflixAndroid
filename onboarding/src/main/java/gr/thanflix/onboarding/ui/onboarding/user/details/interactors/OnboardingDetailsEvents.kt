package gr.thanflix.onboarding.ui.onboarding.user.details.interactors

sealed class OnboardingDetailsEvents {
    class NextStep(val userName: String, val userSurname: String,  val phoneNumber: String): OnboardingDetailsEvents()
}
