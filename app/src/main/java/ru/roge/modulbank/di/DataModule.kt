package ru.roge.modulbank.di

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.RemoteMediator
import androidx.room.Room
import androidx.room.RoomDatabase
import org.koin.dsl.module
import ru.roge.modulbank.core.NetworkConfiguration
import ru.roge.modulbank.core.provideDatabase
import ru.roge.modulbank.core.providePager
import ru.roge.modulbank.data.local.CharactersDatabase
import ru.roge.modulbank.data.local.ResultCharactersEntity
import ru.roge.modulbank.data.repository.ApiStorageImpl
import ru.roge.modulbank.data.repository.CharactersRemoteMediator
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

    single {
        provideDatabase(get())
    }

    single {
        providePager(get(),get())
    }

    single  {
        CharactersRemoteMediator(get(),get())
    }

    factory<GetSingleCharacterRepository> {
        GetSingleCharacterRepositoryImpl(get(),get())
    }

}