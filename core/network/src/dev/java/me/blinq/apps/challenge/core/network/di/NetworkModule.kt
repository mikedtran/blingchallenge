package me.blinq.apps.challenge.core.network.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import me.blinq.apps.challenge.core.network.BlinqNetworkDataSource
import me.blinq.apps.challenge.core.network.retrofit.RetrofitBlinqNetwork
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {
    @Binds
    fun bindsBlinqNetwork(
        blinqNetwork: RetrofitBlinqNetwork
    ): BlinqNetworkDataSource

    companion object {
        @Provides
        @Singleton
        fun providesNetworkJson(): Json = Json {
            ignoreUnknownKeys = true
        }
    }
}
