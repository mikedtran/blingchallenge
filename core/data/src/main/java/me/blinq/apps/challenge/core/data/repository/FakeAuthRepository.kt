package me.blinq.apps.challenge.core.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.blinq.apps.challenge.core.network.BlinqNetworkDataSource
import me.blinq.apps.challenge.core.network.model.FakeAuthRequest
import javax.inject.Inject

class FakeAuthRepository @Inject constructor(
    private val network: BlinqNetworkDataSource
): AuthRepository {
    override fun fakeAuth(name: String, email: String): Flow<Unit> = flow {
        emit(network.fakeAuth(FakeAuthRequest(name = name, email = email)))
    }
}