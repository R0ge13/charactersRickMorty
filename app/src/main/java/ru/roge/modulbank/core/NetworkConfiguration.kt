package ru.roge.modulbank.core

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json



class NetworkConfiguration {

    val androidClient = HttpClient(Android) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.HEADERS
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        install(HttpRequestRetry) {
            maxRetries = 5
            retryIf { request, response ->
                !response.status.isSuccess()
            }
            delayMillis { retry ->
                retry * 3000L
            }
        }

        defaultRequest {
            url(BASE_URL)
        }
    }

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
    }
}