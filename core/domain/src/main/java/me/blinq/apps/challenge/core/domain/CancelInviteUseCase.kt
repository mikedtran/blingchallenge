package me.blinq.apps.challenge.core.domain

import me.blinq.apps.challenge.core.data.repository.UserDataRepository
import javax.inject.Inject

class CancelInviteUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke() {
        userDataRepository.clearUserData()
    }
}