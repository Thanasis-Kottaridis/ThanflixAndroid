package gr.thanflix.presentation.utils

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import gr.thanflix.data.network.api.TMDBApi
import gr.thanflix.data.repository.MoviesRepositoryImpl
import gr.thanflix.data.repository.SeriesRepositoryImpl
import gr.thanflix.domain.repository.MoviesRepository
import gr.thanflix.domain.repository.SeriesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        api: TMDBApi
    ): MoviesRepository = MoviesRepositoryImpl(api = api)

    @Singleton
    @Provides
    fun provideSeriesRepository(
        api: TMDBApi
    ): SeriesRepository = SeriesRepositoryImpl(api = api)
}