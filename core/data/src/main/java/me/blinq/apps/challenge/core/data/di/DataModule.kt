package me.blinq.apps.challenge.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.blinq.apps.challenge.core.data.repository.AuthRepository
import me.blinq.apps.challenge.core.data.repository.FakeAuthRepository
import me.blinq.apps.challenge.core.data.repository.OfflineFirstUserDataRepository
import me.blinq.apps.challenge.core.data.repository.UserDataRepository
import me.blinq.apps.challenge.core.data.util.ConnectivityManagerNetworkMonitor
import me.blinq.apps.challenge.core.data.util.NetworkMonitor


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    fun bindsUserDataRepository(
        userDataRepository: OfflineFirstUserDataRepository
    ): UserDataRepository

    @Binds
    fun bindsFakeAuthRepository(
        fakeAuthRepository: FakeAuthRepository
    ): AuthRepository

    @Binds
    fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}