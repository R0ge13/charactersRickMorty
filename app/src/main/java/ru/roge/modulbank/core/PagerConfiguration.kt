package ru.roge.modulbank.core

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import ru.roge.modulbank.data.local.CharactersDatabase
import ru.roge.modulbank.data.local.ResultCharactersEntity
import ru.roge.modulbank.data.repository.CharactersRemoteMediator
import ru.roge.modulbank.domain.repository.ApiStorage

@OptIn(ExperimentalPagingApi::class)
fun providePager(db: CharactersDatabase, apiStorage: ApiStorage): Pager<Int, ResultCharactersEntity> {
    return Pager(
        config = PagingConfig(20),
        remoteMediator = CharactersRemoteMediator(
            charactersDatabase = db,
            apiStorage = apiStorage
        ),
        pagingSourceFactory = {
            db.dao.pagingSource()
        }
    )
}