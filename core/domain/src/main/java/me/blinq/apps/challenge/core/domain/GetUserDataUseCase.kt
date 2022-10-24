package me.blinq.apps.challenge.core.domain

import kotlinx.coroutines.flow.Flow
import me.blinq.apps.challenge.core.data.repository.UserDataRepository
import me.blinq.apps.challenge.core.model.UserData
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(): Flow<UserData> =
        userDataRepository.userDataStream

}