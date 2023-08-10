package ru.roge.modulbank.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import ru.roge.modulbank.domain.models.LocationCharacter
import ru.roge.modulbank.domain.models.OriginCharacter

@Entity
data class ResultCharactersEntity(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)


