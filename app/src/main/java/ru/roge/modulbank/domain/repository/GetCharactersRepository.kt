package ru.roge.modulbank.domain.repository

import ru.roge.modulbank.domain.models.Characters
import ru.roge.modulbank.utill.Resource

interface GetCharactersRepository {
    suspend fun getCharactersList(page: Int) : Resource<Characters>
}