package me.blinq.apps.challenge.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.blinq.apps.challenge.core.model.UserData

interface UserDataRepository {
    /**
     * Stream of [UserData]
     */
    val userDataStream: Flow<UserData>

    suspend fun saveUserData(name: String, email: String)

    suspend fun clearUserData()
}