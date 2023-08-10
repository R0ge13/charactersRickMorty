package ru.roge.modulbank.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import io.ktor.client.plugins.ServerResponseException
import ru.roge.modulbank.data.local.CharactersDatabase
import ru.roge.modulbank.data.local.ResultCharactersEntity
import ru.roge.modulbank.domain.mappers.toDetailCharacter
import ru.roge.modulbank.domain.repository.ApiStorage
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CharactersRemoteMediator(
    private val charactersDatabase: CharactersDatabase,
    private val apiStorage: ApiStorage,

): RemoteMediator<Int, ResultCharactersEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultCharactersEntity>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val characters = apiStorage.getCharacters(page = loadKey)

            charactersDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    charactersDatabase.dao.clearAll()
                }
                val charactersEntities = characters.results.map { it.toDetailCharacter() }
                charactersDatabase.dao.upsertAll(charactersEntities)
            }
            MediatorResult.Success(
                endOfPaginationReached = characters.results.isEmpty()
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: ServerResponseException) {
            MediatorResult.Error(e)
        }
    }

}