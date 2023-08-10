package ru.roge.modulbank.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import ru.roge.modulbank.domain.models.ResultCharacter

@Composable
fun CharacterItem(
    character: ResultCharacter,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .height(200.dp)
            .width(130.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(Color.DarkGray)
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        SubcomposeAsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .size(100.dp)
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
    }
}

@Preview
@Composable
fun PreviewCharacterItem() {
    MaterialTheme {
        //CharacterItem(CharacterShort(id = 0,"Rick", "Alive", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"), onClick = {})
    }
}