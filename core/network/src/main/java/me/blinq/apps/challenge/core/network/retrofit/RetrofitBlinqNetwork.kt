package me.blinq.apps.challenge.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import me.blinq.apps.challenge.core.network.BlinqNetworkDataSource
import me.blinq.apps.challenge.core.network.BuildConfig
import me.blinq.apps.challenge.core.network.ApiResponseException
import me.blinq.apps.challenge.core.network.model.NetworkError
import me.blinq.apps.challenge.core.network.model.FakeAuthRequest
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for Blinq Challenge Network API
 */
private interface RetrofitBlinqNetworkApi {
    @POST("/fakeAuth")
    suspend fun fakeAuth(@Body request: FakeAuthRequest)
}

private const val BlinqBaseUrl = BuildConfig.BACKEND_URL

@Singleton
class RetrofitBlinqNetwork @Inject constructor(
    networkJson: Json
) : BlinqNetworkDataSource {
    private val client = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            if (!response.isSuccessful) {
                if (response.code == 400) {
                    response.body?.string()?.let {
                        val networkError = networkJson.decodeFromString<NetworkError>(it)
                        throw ApiResponseException(networkError.errorMessage)
                    }
                }
            }
            response
        })
        .build()
    private val networkApi = Retrofit.Builder()
        .baseUrl(BlinqBaseUrl)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(
            @OptIn(ExperimentalSerializationApi::class)
            networkJson.asConverterFactory("application/json".toMediaType())
        )
        .build()
        .create(RetrofitBlinqNetworkApi::class.java)

    override suspend fun fakeAuth(request: FakeAuthRequest) =
        networkApi.fakeAuth(request)
}