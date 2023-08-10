package ru.roge.modulbank.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ResultCharactersEntity::class],
    version = 1
)
abstract class CharactersDatabase: RoomDatabase() {
    abstract val dao: CharactersDao
}