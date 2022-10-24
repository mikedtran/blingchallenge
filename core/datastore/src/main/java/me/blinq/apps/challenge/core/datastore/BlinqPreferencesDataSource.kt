package me.blinq.apps.challenge.core.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.blinq.apps.challenge.core.model.UserData
import java.io.IOException
import javax.inject.Inject

class BlinqPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userDataStream = userPreferences.data
        .map {
            UserData(
                name = it.name,
                email = it.email
            )
        }

    suspend fun saveUserData(name: String, email: String) {
        try {
            userPreferences.updateData {
                it.copy {
                    this.name = name
                    this.email = email
                }
            }
        } catch (ioException: IOException) {
            Log.e("BlinqPreferences", "Failed to update user preferences", ioException)
        }

    }

    suspend fun clearUserData() {
        try {
            userPreferences.updateData {
                it.copy {
                    name = ""
                    email = ""
                }
            }
        } catch (ioException: IOException) {
            Log.e("BlinqPreferences", "Failed to update user preferences", ioException)
        }
    }
}