package org.josedev.house_control.auth

import kotlinx.browser.window
import org.josedev.house_control.utils.Constants.ACCESS_TOKEN_KEY
import org.josedev.house_control.utils.Constants.REFRESH_TOKEN_KEY

class JsTokenStorage : TokenStorage {
    override fun saveAccessToken(token: String) {
        window.localStorage.setItem(ACCESS_TOKEN_KEY, token)
    }

    override fun getAccessToken(): String? {
        return window.localStorage.getItem(ACCESS_TOKEN_KEY)
    }

    override fun saveRefreshToken(token: String) {
        window.localStorage.setItem(REFRESH_TOKEN_KEY, token)
    }

    override fun getRefreshToken(): String? {
        return window.localStorage.getItem(REFRESH_TOKEN_KEY)
    }

    override fun clearTokens() {
        window.localStorage.removeItem(ACCESS_TOKEN_KEY)
        window.localStorage.removeItem(REFRESH_TOKEN_KEY)
    }
}