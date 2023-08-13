package gr.thanflix.data.di

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import gr.thanflix.data.network.api.BaseHttpClientModule
import gr.thanflix.data.network.api.TMDBApi
import gr.thanflix.domain.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class TMDBHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()

    @TMDBHttpClient
    @Provides
    @Singleton
    fun provideTMDBHttpClient(
        @ApplicationContext
        context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = BaseHttpClientModule(
        context,
        httpLoggingInterceptor
    ).provideTMDBHttpClientModule()

    @Singleton
    @Provides
    fun provideTMDBService(
        @TMDBHttpClient okHttpClient: OkHttpClient,
    ): TMDBApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .client(okHttpClient)
            .build()
            .create(TMDBApi::class.java)
    }
}