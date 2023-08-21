package gr.thanflix.presentation.base.ui.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.base.ui.compose.theme.ThanflixAndroidTheme

abstract class ComposeBaseFragment(
    layoutResId: Int
) : BaseFragment(layoutResId)  {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ThanflixAndroidTheme {
                    ComposableContent()
                }
            }
        }
    }

    // Define an abstract function that returns a Composable
    @Composable
    abstract fun ComposableContent()

    /**
     * Empty Implementation of SetUpView
     *
     * Because no need to instantiate Regular views.
     * all views will be handled via composable.
     */
    override fun setupView() {}
}