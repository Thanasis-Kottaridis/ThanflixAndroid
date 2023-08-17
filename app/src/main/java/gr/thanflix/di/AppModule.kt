package gr.thanflix.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.thanflix.base.BaseErrorDispatcherImpl
import gr.thanflix.presentation.base.viewModel.BaseErrorDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideBaseErrorDispatcher(
        @ApplicationContext context: Context
    ): BaseErrorDispatcher = BaseErrorDispatcherImpl(context)
}
