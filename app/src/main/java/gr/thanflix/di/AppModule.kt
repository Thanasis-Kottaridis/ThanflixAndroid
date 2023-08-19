package gr.thanflix.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.thanflix.base.BaseErrorDispatcherImpl
import gr.thanflix.coordinator.MainAppCoordinator
import gr.thanflix.presentation.base.navigation.BaseActionHandler
import gr.thanflix.presentation.base.navigation.Coordinator
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMainAppCoordinator(
        coordinator: MainAppCoordinator
    ): Coordinator = MainAppCoordinator()

    @Provides
    @Singleton
    fun provideActionHandler(
        mainAppCoordinator: Coordinator
    ): BaseActionHandler = mainAppCoordinator

    @Provides
    @Singleton
    fun provideBaseErrorDispatcher(
        @ApplicationContext context: Context
    ): BaseErrorDispatcher = BaseErrorDispatcherImpl(context)
}
