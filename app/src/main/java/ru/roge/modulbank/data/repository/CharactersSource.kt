package ru.roge.modulbank.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.roge.modulbank.domain.models.ResultCharacter
import ru.roge.modulbank.domain.repository.GetCharactersRepository

class CharactersSource(private val getCharactersRepository: GetCharactersRepository):
    PagingSource<Int, ResultCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, ResultCharacter>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultCharacter> {
        return try {
            val page = params.key ?: 1
            val charactersResponse = getCharactersRepository.getCharactersList(page)
            LoadResult.Page(
                data = charactersResponse.data!!.results ,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page.plus(1)
            )
        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}