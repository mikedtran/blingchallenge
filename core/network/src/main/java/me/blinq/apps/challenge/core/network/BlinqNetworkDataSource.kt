package me.blinq.apps.challenge.core.network

import me.blinq.apps.challenge.core.network.model.FakeAuthRequest
import retrofit2.http.Body

interface BlinqNetworkDataSource {
    suspend fun fakeAuth(@Body request: FakeAuthRequest)
}