package ru.roge.modulbank.data.repository

import android.content.Context
import io.ktor.client.plugins.ServerResponseException
import ru.roge.modulbank.R
import ru.roge.modulbank.domain.models.ResultCharacter
import ru.roge.modulbank.domain.repository.ApiStorage
import ru.roge.modulbank.domain.repository.GetSingleCharacterRepository
import ru.roge.modulbank.utill.Resource
import ru.roge.modulbank.utill.checkInternetConnection
import java.io.IOException

class GetSingleCharacterRepositoryImpl(
    private val apiStorage: ApiStorage,
    private val context: Context
) : GetSingleCharacterRepository {
    override suspend fun getCharacter(characterId: Int): Resource<ResultCharacter> {
        if (!context.checkInternetConnection()) {
            return Resource.Error(context.getString(R.string.error_internet_turned_off))
        }
        val response = try {
            apiStorage.getSingleCharacter(characterId)
        } catch (e: ServerResponseException) {
            return Resource.Error(context.getString(R.string.error_http))
        } catch (e: IOException) {
            return Resource.Error(context.getString(R.string.check_internet_connection))
        }
        return Resource.Success(response)
    }

}
