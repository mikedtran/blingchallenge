package me.blinq.apps.challenge.core.data.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun fakeAuth(name: String, email: String): Flow<Unit>
}