package org.josedev.house_control.auth

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.josedev.house_control.utils.Constants.ACCESS_TOKEN_KEY
import androidx.core.content.edit
import org.josedev.house_control.utils.Constants.REFRESH_TOKEN_KEY

class AndroidTokenStorage(context: Context) : TokenStorage {
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs = EncryptedSharedPreferences.create(
        context,
        "secret_file",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    override fun saveAccessToken(token: String) {
        prefs.edit { putString(ACCESS_TOKEN_KEY, token) }
    }

    override fun getAccessToken(): String? = prefs.getString(ACCESS_TOKEN_KEY, null)

    override fun saveRefreshToken(token: String) {
        prefs.edit { putString(REFRESH_TOKEN_KEY, token) }
    }

    override fun getRefreshToken(): String? = prefs.getString(REFRESH_TOKEN_KEY, null)

    override fun clearTokens() {
        prefs.edit { clear() }
    }
}