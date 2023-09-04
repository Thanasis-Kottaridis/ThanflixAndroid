package gr.thanflix.onboarding.ui.authorization

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.onboarding.R
import gr.thanflix.onboarding.ui.authorization.components.LoginScreen
import gr.thanflix.presentation.base.ui.compose.ComposeBaseFragment

@AndroidEntryPoint
class LoginFragment: ComposeBaseFragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    /**
     * Set navbar visibility
     */
    override var showNavigationBar: Boolean = false

    override fun populateData() {}

    /**
     * Composable Content
     */
    @Composable
    override fun ComposableContent() {
        val state by viewModel.state.collectAsState()

        LoginScreen(
            state = state,
            onTriggerEvent = viewModel::onTriggerEvent
        )
    }
}