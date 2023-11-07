package com.suslanium.filmus.data.datasource

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.suslanium.filmus.data.Constants

class TokenDataSource(context: Context) {

    private val masterKeyAlias =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        Constants.TOKEN_PREFERENCES,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun fetchToken(): String? {
        return sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(Constants.ACCESS_TOKEN, token).apply()
    }

    fun deleteToken() {
        sharedPreferences.edit().remove(Constants.ACCESS_TOKEN).apply()
    }

}