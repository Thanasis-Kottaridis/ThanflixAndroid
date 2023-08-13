package gr.thanflix.data.network.api

import android.content.Context
import com.google.gson.internal.GsonBuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import gr.thanflix.domain.BuildConfig
import okhttp3.Cache
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.internal.http.RealResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.GzipSource
import okio.buffer
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BaseHttpClientModule @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val httpLoggingInterceptor: HttpLoggingInterceptor
    ) {

    companion object {
        /**
         * Client Configuration Constants
         */
        private const val CACHE_DIRECTORY = "OKHttpCache"
        private const val CACHE_MAX_SIZE = (10 * 1024 * 1024).toLong()
        private const val READ_TIMEOUT = 30L
        private const val CONNECT_TIMEOUT= 30L

        private val HTTP_PROTOCOLS = listOf(Protocol.HTTP_2, Protocol.HTTP_1_1)

        /**
         * JWT HEADER COSTS
         */
        private const val ACCOUNT_ID = "20091904"
        private const val API_KEY = "40bcc56bcbc19c6715ac1eec3580d78e"
        private const val API_ACCESS_TOKEN ="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0MGJjYzU2YmNiYzE5YzY3MTVhYzFlZWMzNTgwZDc4ZSIsInN1YiI6IjY0YTAzY2ZjOGMwYTQ4MDBjNzYzZDRiMCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6BM8aYSJ_L4CMAO-8zKlgfViqA4eWMYkilTY2FnRMjg"
    }

    private val cacheDirectory = File(context.cacheDir, CACHE_DIRECTORY)
    private lateinit var userAgent: String

    fun provideTMDBHttpClientModule(): OkHttpClient {
//        BuildConfi
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .cache(Cache(cacheDirectory, CACHE_MAX_SIZE))
            .readTimeout(TimeUnit.SECONDS.toSeconds(READ_TIMEOUT), TimeUnit.SECONDS)
            .connectTimeout(TimeUnit.SECONDS.toSeconds(CONNECT_TIMEOUT), TimeUnit.SECONDS)
            .protocols(HTTP_PROTOCOLS)
            .addInterceptor(UnzippingInterceptor())
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(TMDBClientInterceptor())
            .build()
    }

    private class UnzippingInterceptor : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val response = chain.proceed(chain.request())
            return unzip(response)
        }

        private fun unzip(response: Response): Response {
            if (response.body == null) {
                return response;
            }

            //check if we have gzip response
            val contentEncoding = response.headers["Content-Encoding"]

            return if (contentEncoding != null && contentEncoding == "gzip") {
                val contentLength = response.body!!.contentLength()
                val responseBody = GzipSource(response.body!!.source())
                val strippedHeaders: Headers = response.headers.newBuilder().build()
                response.newBuilder().headers(strippedHeaders)
                    .body(
                        RealResponseBody(response.body!!.contentType().toString(),
                        contentLength,
                        responseBody.buffer())
                    )
                    .build()
            } else {
                response
            }
        }
    }

    /**
     * This interceptor adds headers required to communicate with TMDB Backend
     */
    private class TMDBClientInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()

            val authenticatedRequest = request.newBuilder()

            authenticatedRequest.header("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN!!}")
            // TODO ADD MORE HEADERS HERE.

            return chain.proceed(authenticatedRequest.build())
        }

    }
}