package me.blinq.apps.challenge.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(
    val errorMessage: String
)