package ru.roge.modulbank.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Characters(
    val info: InfoCharacter?,
    val results: List<ResultCharacter>
)

@Serializable
data class InfoCharacter(
    val count: Int,
    val next: String?,
    val pages: Int,
    val prev: String? = null
)

@Serializable
data class ResultCharacter(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: LocationCharacter,
    val name: String,
    val origin: OriginCharacter,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

@Serializable
data class OriginCharacter(
    val name: String,
    val url: String
)

@Serializable
data class LocationCharacter(
    val name: String,
    val url: String
)