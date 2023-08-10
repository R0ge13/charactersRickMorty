package ru.roge.modulbank.core

import android.content.Context
import androidx.room.Room
import ru.roge.modulbank.data.local.CharactersDatabase

fun provideDatabase(context: Context): CharactersDatabase {
    return Room.databaseBuilder(
        context,
        CharactersDatabase::class.java,
        "characters.db"
    ).build()
}