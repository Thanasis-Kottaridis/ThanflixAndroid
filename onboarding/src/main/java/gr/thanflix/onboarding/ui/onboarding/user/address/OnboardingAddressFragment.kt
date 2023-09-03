package gr.thanflix.onboarding.ui.onboarding.user.address

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.viewModels
import androidx.navigation.navGraphViewModels
import gr.thanflix.onboarding.R
import gr.thanflix.onboarding.ui.onboarding.user.mediators.OnboardingMediator
import gr.thanflix.presentation.base.ui.compose.ComposeBaseFragment

class OnboardingAddressFragment : ComposeBaseFragment(R.layout.fragment_onboarding_address) {

    private val mediator: OnboardingMediator by navGraphViewModels(R.id.onboarding_nav_graph) { defaultViewModelProviderFactory }

    private val viewModel: OnboardingAddressViewModel by viewModels()

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