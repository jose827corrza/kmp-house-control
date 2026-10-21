package org.josedev.house_control.di

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.josedev.house_control.auth.TokenStorage
import org.josedev.house_control.data.client.AuthApi
import org.josedev.house_control.data.client.AuthApiImpl
import org.josedev.house_control.data.client.ClientApi
import org.josedev.house_control.data.client.ClientApiImpl
import org.josedev.house_control.utils.Constants.AUTH_BASE_URL
import org.josedev.house_control.utils.Constants.AUTH_QUALIFIER
import org.josedev.house_control.utils.Constants.BACKEND_QUALIFIER
import org.josedev.house_control.utils.Constants.BASE_URL
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single(named(AUTH_QUALIFIER)) {
        authHttpClient()
    }
    single(named(BACKEND_QUALIFIER)) {
        backendHttpClient(get())
    }

    single { AuthApiImpl(get(named(AUTH_QUALIFIER)), get()) } bind AuthApi::class
    single { ClientApiImpl(get(named(BACKEND_QUALIFIER)), get()) } bind ClientApi::class
}

fun authHttpClient() = HttpClient {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }, contentType = ContentType.parse("application/x-amz-json-1.1")
        )
    }

    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println(message)
            }
        }
        level = LogLevel.HEADERS
    }

    defaultRequest {
        url(AUTH_BASE_URL)
    }
}

fun backendHttpClient(tokenStorage: TokenStorage) = HttpClient {

    install(Auth) {
        bearer {
            loadTokens {
                val accessToken = tokenStorage.getAccessToken()
                val refreshToken = tokenStorage.getRefreshToken()

                if (accessToken != null && refreshToken != null) {
                    BearerTokens(accessToken, refreshToken)
                } else null
            }

//            refreshTokens {
//                val currentRefresh = tokenStorage.getRefreshToken() ?: return@refreshTokens null
//                val newTokens = "performCognitoRefresh(currentRefresh)"
//
//                tokenStorage.saveAccessToken(newTokens)
//
//                if (newTokens)
//            }
        }
    }
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
    install(Logging) {
        logger = object : Logger {
            override fun log(message: String) {
                println(message)
            }
        }
        level = LogLevel.ALL
    }
    defaultRequest {
        url(BASE_URL)
    }
}