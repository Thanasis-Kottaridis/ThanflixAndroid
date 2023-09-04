package gr.thanflix.onboarding.ui.authorization.interactors

sealed class LoginEvents {
    object GoToOnboarding : LoginEvents()
    object GoToMainApp: LoginEvents()
}
