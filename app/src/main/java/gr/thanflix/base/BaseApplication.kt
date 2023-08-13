package gr.thanflix.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import gr.thanflix.domain.BuildConfig
import timber.log.Timber

/**
 * This annotation "HiltAndroidApp"
 * generates dagger top Level component => app component
 *
 * Hilt automatically generates dagger components for different scopes/lifecycles
 * -> SingletonComponent => Application Scope
 * -> ActivityRetainedComponent => Activity Scope.
 * -> ViewModelComponent => ViewModel Scope.
 * -> ActivityComponent => Activity Scope.
 * -> FragmentComponent => Fragment Scope.
 * -> ViewComponent => View Scope.
 * -> ServiceComponent => Service Scope
 *
 * So for example every dependency is defined in ApplicationComponent lives as long application is alive
 *
 * Hilt documentation:
 * https://developer.android.com/training/dependency-injection/hilt-android#component-lifetimes
 */
@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}