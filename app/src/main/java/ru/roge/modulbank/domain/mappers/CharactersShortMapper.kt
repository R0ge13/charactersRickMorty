package ru.roge.modulbank.domain.mappers

import ru.roge.modulbank.domain.models.CharacterShort
import ru.roge.modulbank.domain.models.ResultCharacter


fun ResultCharacter.toCharacter(): CharacterShort {
    return CharacterShort(
        id = this.id,
        name = this.name,
        status = this.status,
        image = this.image
    )
}



