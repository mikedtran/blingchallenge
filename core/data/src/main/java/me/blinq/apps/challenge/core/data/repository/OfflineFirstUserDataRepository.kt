package me.blinq.apps.challenge.core.data.repository

import kotlinx.coroutines.flow.Flow
import me.blinq.apps.challenge.core.datastore.BlinqPreferencesDataSource
import me.blinq.apps.challenge.core.model.UserData
import javax.inject.Inject

class OfflineFirstUserDataRepository @Inject constructor(
    private val blinqPreferencesDataSource: BlinqPreferencesDataSource
) : UserDataRepository {
    override val userDataStream: Flow<UserData> =
        blinqPreferencesDataSource.userDataStream

    override suspend fun saveUserData(name: String, email: String) {
        blinqPreferencesDataSource.saveUserData(name, email)
    }

    override suspend fun clearUserData() =
        blinqPreferencesDataSource.clearUserData()
}