package org.josedev.house_control.auth

import org.josedev.house_control.utils.Constants.ACCESS_TOKEN_KEY
import org.josedev.house_control.utils.Constants.REFRESH_TOKEN_KEY
import java.util.prefs.Preferences

class JvmTokenStorage : TokenStorage {
    private val prefs: Preferences = Preferences.userNodeForPackage(JvmTokenStorage::class.java)

    override fun saveAccessToken(token: String) {
        prefs.put(ACCESS_TOKEN_KEY, token)
    }

    override fun getAccessToken(): String? = prefs.get(ACCESS_TOKEN_KEY, null)

    override fun saveRefreshToken(token: String) {
        prefs.put(REFRESH_TOKEN_KEY, token)
    }

    override fun getRefreshToken(): String? = prefs.get(REFRESH_TOKEN_KEY, null)

    override fun clearTokens() {
        prefs.remove(REFRESH_TOKEN_KEY)
        prefs.remove(ACCESS_TOKEN_KEY)
        prefs.flush()
    }
}