package me.blinq.apps.challenge.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FakeAuthRequest(
    val name: String,
    val email: String
)