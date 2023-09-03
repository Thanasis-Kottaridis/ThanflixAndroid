package gr.thanflix.onboarding.ui.onboarding.user.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.onboarding.R
import gr.thanflix.onboarding.ui.onboarding.user.mediators.OnboardingMediator
import gr.thanflix.presentation.base.ui.compose.ComposeBaseFragment

@AndroidEntryPoint
class OnboardingDetailsFragment: ComposeBaseFragment(R.layout.fragment_onboarding_details) {

    private val mediator: OnboardingMediator by navGraphViewModels(R.id.onboarding_nav_graph) { defaultViewModelProviderFactory }

    private val viewModel: OnboardingDetailsViewModel by viewModels()

    /**
     * Set navbar visibility
     */
    override var showNavigationBar: Boolean = false

    override fun setupView() {
        super.setupView()
        viewModel.injectMediator(mediator)
    }

    override fun populateData() {}

    /**
     * Composable Content
     */
    @Composable
    override fun ComposableContent() {
        val state by viewModel.state.collectAsState()
    }
}