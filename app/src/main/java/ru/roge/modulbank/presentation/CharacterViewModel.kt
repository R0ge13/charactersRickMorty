package ru.roge.modulbank.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import ru.roge.modulbank.data.local.ResultCharactersEntity
import ru.roge.modulbank.domain.repository.GetSingleCharacterRepository
import ru.roge.modulbank.utill.BaseViewModel

class CharacterViewModel : BaseViewModel() {



    private val getSingleCharacterRepository: GetSingleCharacterRepository by inject()

    private val pager: Pager<Int,ResultCharactersEntity> by inject()

    //private val _detailCharactersList: MutableStateFlow<?> = MutableStateFlow(null)
   // val detailCharactersList = _detailCharactersList.asStateFlow()


    val charactersPagingFlow = pager
        .flow
        .map { pagingData->
            pagingData.map {
                it
            }
        }.cachedIn(viewModelScope)
//    fun getCharactersPagination(): Flow<PagingData<ResultCharacter>> {
//        return Pager(
//            PagingConfig(
//                pageSize = 5,
//                enablePlaceholders = true
//            )
//        ) {
//            CharactersSource(getCharactersRepository)
//        }.flow
//    }

    fun getDetailInfoAboutCharacter(id: Int) {
        viewModelScope.launch {
     //       _detailCharactersList.value = getSingleCharacterRepository.getCharacter(id).data?.toDetailCharacter()
        }
    }

}