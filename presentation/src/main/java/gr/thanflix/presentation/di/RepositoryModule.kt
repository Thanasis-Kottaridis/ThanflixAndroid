package gr.thanflix.presentation.utils

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.thanflix.data.network.api.TMDBApi
import gr.thanflix.data.repository.MoviesRepositoryImpl
import gr.thanflix.domain.repository.MoviesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: TMDBApi
    ): MoviesRepository = MoviesRepositoryImpl(api = api)
}