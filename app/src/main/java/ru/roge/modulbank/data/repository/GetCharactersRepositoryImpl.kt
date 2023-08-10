package ru.roge.modulbank.data.repository

import android.content.Context
import io.ktor.client.plugins.ServerResponseException
import ru.roge.modulbank.R
import ru.roge.modulbank.domain.models.Characters
import ru.roge.modulbank.domain.repository.ApiStorage
import ru.roge.modulbank.domain.repository.GetCharactersRepository
import ru.roge.modulbank.utill.Resource
import ru.roge.modulbank.utill.checkInternetConnection
import java.io.IOException

class GetCharactersRepositoryImpl(
    private val apiStorage: ApiStorage,
    private val context: Context
): GetCharactersRepository {
    override suspend fun getCharactersList(page: Int): Resource<Characters> {
        if(!context.checkInternetConnection()) {
            return Resource.Error(context.getString(R.string.error_internet_turned_off))
        }

        val response = try {
            apiStorage.getCharacters(page)
        } catch (e: ServerResponseException) {
            return Resource.Error(context.getString(R.string.error_http))
        } catch (e: IOException) {
            return Resource.Error(context.getString(R.string.check_internet_connection))
        }

        return if(response.info != null ) {
            Resource.Success(response)
        } else {
            Resource.Error(context.getString(R.string.error_unknown))
        }
    }

}