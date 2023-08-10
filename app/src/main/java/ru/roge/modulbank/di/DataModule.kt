package ru.roge.modulbank.di

import org.koin.dsl.module
import ru.roge.modulbank.core.NetworkConfiguration
import ru.roge.modulbank.data.repository.ApiStorageImpl
import ru.roge.modulbank.data.repository.GetCharactersRepositoryImpl
import ru.roge.modulbank.data.repository.GetSingleCharacterRepositoryImpl
import ru.roge.modulbank.domain.repository.ApiStorage
import ru.roge.modulbank.domain.repository.GetCharactersRepository
import ru.roge.modulbank.domain.repository.GetSingleCharacterRepository
import ru.roge.modulbank.presentation.CharacterViewModel

fun dataModule() = module {

    single {
        NetworkConfiguration()
    }

    single<ApiStorage> {
        ApiStorageImpl(get())
    }

    single {
        CharacterViewModel()
    }

    single <GetCharactersRepository> {
        GetCharactersRepositoryImpl(get(),get())
    }

    factory<GetSingleCharacterRepository> {
        GetSingleCharacterRepositoryImpl(get(),get())
    }

}