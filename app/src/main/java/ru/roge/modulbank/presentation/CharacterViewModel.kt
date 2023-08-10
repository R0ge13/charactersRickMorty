package ru.roge.modulbank.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import ru.roge.modulbank.data.repository.CharactersSource
import ru.roge.modulbank.domain.mappers.toDetailCharacter
import ru.roge.modulbank.domain.models.CharacterDetails
import ru.roge.modulbank.domain.models.ResultCharacter
import ru.roge.modulbank.domain.repository.GetCharactersRepository
import ru.roge.modulbank.domain.repository.GetSingleCharacterRepository
import ru.roge.modulbank.utill.BaseViewModel

class CharacterViewModel : BaseViewModel() {

    private val getCharactersRepository: GetCharactersRepository by inject()

    private val getSingleCharacterRepository: GetSingleCharacterRepository by inject()


    private val _detailCharactersList: MutableStateFlow<CharacterDetails?> = MutableStateFlow(null)
    val detailCharactersList = _detailCharactersList.asStateFlow()



    fun getCharactersPagination(): Flow<PagingData<ResultCharacter>> {
        return Pager(
            PagingConfig(
                pageSize = 5,
                enablePlaceholders = true
            )
        ) {
            CharactersSource(getCharactersRepository)
        }.flow
    }

    fun getDetailInfoAboutCharacter(id: Int) {
        viewModelScope.launch {
            _detailCharactersList.value = getSingleCharacterRepository.getCharacter(id).data?.toDetailCharacter()
        }
    }

}