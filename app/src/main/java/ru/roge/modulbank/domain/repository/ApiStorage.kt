package ru.roge.modulbank.domain.repository

import ru.roge.modulbank.domain.models.Characters
import ru.roge.modulbank.domain.models.ResultCharacter

interface ApiStorage {

    suspend fun getCharacters(page: Int): Characters

    suspend fun getSingleCharacter(characterId: Int): ResultCharacter
}