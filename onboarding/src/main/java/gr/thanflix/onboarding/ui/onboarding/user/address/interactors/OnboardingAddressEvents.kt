package gr.thanflix.onboarding.ui.onboarding.user.address.interactors

sealed class OnboardingAddressEvents {
    class NextStep(val streetName: String, val streetNumber: String, val postalCode: String): OnboardingAddressEvents()
}
