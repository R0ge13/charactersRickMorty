package ru.roge.modulbank.domain.repository

import ru.roge.modulbank.domain.models.ResultCharacter
import ru.roge.modulbank.utill.Resource

interface GetSingleCharacterRepository {

    suspend fun getCharacter(characterId:Int): Resource<ResultCharacter>
}