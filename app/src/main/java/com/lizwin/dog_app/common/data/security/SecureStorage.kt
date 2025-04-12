package com.lizwin.dog_app.common.data.security

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.core.content.edit

object SecureStorage {

    private fun getSharedPreferences(context: Context) =
        EncryptedSharedPreferences.create(
            context,
            "secure_prefs",
            MasterKey.Builder(context)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build(),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun saveApiKey(context: Context, apiKey: String) {
        getSharedPreferences(context).edit {
            putString("API_KEY", apiKey)
        }
    }

    fun getApiKey(context: Context): String? {
        return getSharedPreferences(context).getString("API_KEY", null)
    }
}