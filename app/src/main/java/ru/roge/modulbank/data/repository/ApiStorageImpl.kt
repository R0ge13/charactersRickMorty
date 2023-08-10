package ru.roge.modulbank.data.repository

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.roge.modulbank.core.NetworkConfiguration
import ru.roge.modulbank.domain.models.Characters
import ru.roge.modulbank.domain.models.ResultCharacter
import ru.roge.modulbank.domain.repository.ApiStorage

class ApiStorageImpl(private val client: NetworkConfiguration): ApiStorage {
    override suspend fun getCharacters(page: Int): Characters {
        return client.androidClient.get("character") {
            parameter("page",page)
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun getSingleCharacter(characterId: Int): ResultCharacter {
        return client.androidClient.get("character/$characterId") {
            contentType(ContentType.Application.Json)
        }.body()
    }
}