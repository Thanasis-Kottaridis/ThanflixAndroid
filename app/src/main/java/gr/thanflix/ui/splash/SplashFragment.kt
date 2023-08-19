package gr.thanflix.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import gr.thanflix.R
import gr.thanflix.movies.databinding.FragmentMoviesLandingBinding
import gr.thanflix.presentation.base.ui.BaseFragment
import gr.thanflix.presentation.utils.helpers.viewBinding
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val binding by viewBinding(FragmentMoviesLandingBinding::bind)

    override fun setupView() {}

    override fun setUpObservers() {}

    override fun populateData() {}
}