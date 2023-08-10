package ru.roge.modulbank.domain.mappers

import ru.roge.modulbank.data.local.ResultCharactersEntity
import ru.roge.modulbank.domain.models.ResultCharacter

fun ResultCharacter.toDetailCharacter(): ResultCharactersEntity {
    return ResultCharactersEntity(
        created = this.created,
        gender = this.gender,
        id = this.id,
        image = this.image,
        name = this.name,
        species = this.species,
        status = this.status,
        type = this.type,
        url = this.url,
    )
}

