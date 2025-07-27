package br.com.domotest.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull

const val USER_ID_KEY = "user_id"

class LoginLocalDatasourceImpl(
    private val dataStore: DataStore<Preferences>
): LoginLocalDatasource {

    private val userIdPreferencesKey = stringPreferencesKey(USER_ID_KEY)

    override suspend fun saveUserId(userId: String) {
        dataStore.edit { settings ->
            settings[userIdPreferencesKey] = userId
        }
    }

    override suspend fun getUserId(): String? {
        return dataStore.data.firstOrNull()?.get(userIdPreferencesKey)
    }
}