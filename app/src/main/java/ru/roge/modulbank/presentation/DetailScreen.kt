package ru.roge.modulbank.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import ru.roge.modulbank.domain.models.CharacterDetails

@Composable
fun DetailScreen(
    characterDetails: CharacterDetails?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        when (characterDetails) {
            null -> CircularProgressIndicator()
            else -> {
                SubcomposeAsyncImage(
                    model = characterDetails.image,
                    contentDescription = characterDetails.name,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                ) {
                    SubcomposeAsyncImageContent()
                }
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Name: ${characterDetails.name}",
                        style = MaterialTheme.typography.bodyLarge
                        )
                    Text(
                        text = "Status: ${characterDetails.status}",
                        style = MaterialTheme.typography.bodyMedium,
                        )
                    Text(text = "Created: ${characterDetails.created}")
                    Text(text = "Gender: ${characterDetails.gender}")
                    Text(text = "Spices: ${characterDetails.species}")
                    Text(text = "Type: ${characterDetails.type}")
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen() {
    MaterialTheme {
        //   DetailScreen()
    }
}