package ru.roge.modulbank.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import ru.roge.modulbank.data.local.ResultCharactersEntity
import ru.roge.modulbank.domain.models.ResultCharacter

@Composable
fun CharacterItem(
    character: ResultCharactersEntity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .background(Color.DarkGray)
            .padding(8.dp),


    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White, CircleShape),
                contentScale = ContentScale.Crop
            ) {
                when (painter.state) {
                    AsyncImagePainter.State.Empty -> {
                        HelpIcon(icon = Icons.Rounded.Person)
                    }

                    is AsyncImagePainter.State.Error -> {
                        HelpIcon(
                            icon = Icons.Rounded.Info,
                            tint = Color.Red
                        )
                    }

                    is AsyncImagePainter.State.Loading -> {
                        CircularProgressIndicator(
                            color = Color.Gray
                        )
                    }

                    is AsyncImagePainter.State.Success -> {
                        SubcomposeAsyncImageContent()
                    }
                }
            }
            Column(
                modifier = Modifier.padding(start = 16.dp)
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
                Text(
                    text = character.status,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.LightGray,
                )
                Text(
                    text = "Name: ${character.name}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Status: ${character.status}",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(text = "Created: ${character.created}")
                Text(text = "Gender: ${character.gender}")
                Text(text = "Spices: ${character.species}")
                Text(text = "Type: ${character.type}")
            }
        }
    }
}

@Preview
@Composable
fun PreviewCharacterItem() {
    MaterialTheme {
        //CharacterItem(CharacterShort(id = 0,"Rick", "Alive", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"), onClick = {})
    }
}