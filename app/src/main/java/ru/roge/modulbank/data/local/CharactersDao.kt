package ru.roge.modulbank.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import ru.roge.modulbank.domain.models.ResultCharacter

@Dao
interface CharactersDao {

    @Upsert
    fun upsertAll(characters: List<ResultCharactersEntity>)

    @Query("SELECT * FROM ResultCharactersEntity")
    fun pagingSource(): PagingSource<Int,ResultCharactersEntity>

    @Query("DELETE FROM ResultCharactersEntity")
    fun clearAll()
}