package me.blinq.apps.challenge.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import me.blinq.apps.challenge.core.data.repository.AuthRepository
import me.blinq.apps.challenge.core.data.repository.UserDataRepository
import javax.inject.Inject

class RequestInviteUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(name: String, email: String): Flow<Unit> =
        authRepository.fakeAuth(name, email)
            .flatMapLatest {
                userDataRepository.saveUserData(name, email)
                flowOf(Unit)
            }
}