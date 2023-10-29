package com.suslanium.filmus.data.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.suslanium.filmus.data.Constants
import com.suslanium.filmus.data.remote.api.UserApiService
import com.suslanium.filmus.domain.entity.user.UserProfile
import kotlinx.coroutines.flow.first
import java.util.UUID

private val Context.userDataStore by preferencesDataStore(Constants.USER_PREFERENCES)

class UserDataSource(
    private val userApiService: UserApiService, private val context: Context
) {

    private val dataStoreUserIdKey = stringPreferencesKey(Constants.USER_ID_KEY)

    //TODO add unauthorized exception handling
    suspend fun getUserProfile(): UserProfile = userApiService.getUserProfile()

    suspend fun changeUserProfile(userProfile: UserProfile) = userApiService.changeUserProfile(userProfile)

    private suspend fun saveUserId(id: UUID) {
        context.userDataStore.edit { dataStore ->
            dataStore[dataStoreUserIdKey] = id.toString()
        }
    }

    suspend fun fetchUserId(): UUID {
        val preferences = context.userDataStore.data.first()
        if (preferences[dataStoreUserIdKey] != null) return UUID.fromString(preferences[dataStoreUserIdKey])
        val user = getUserProfile()
        saveUserId(user.id)
        return user.id
    }

}