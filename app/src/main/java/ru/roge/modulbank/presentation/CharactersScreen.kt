package ru.roge.modulbank.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch
import ru.roge.modulbank.R
import ru.roge.modulbank.presentation.components.CharacterItem
import ru.roge.modulbank.presentation.components.ErrorView
import java.net.UnknownHostException

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharactersScreen(viewModel: CharacterViewModel) {

    val characters = viewModel.getCharactersPagination().collectAsLazyPagingItems()
    val detailCharacter = viewModel.detailCharactersList.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState =
        rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden,
            skipHalfExpanded = true
        )


    ModalBottomSheetLayout(
        sheetContent = {
            DetailScreen(
                characterDetails = detailCharacter.value
            )
        },
        sheetState = bottomSheetState,
        sheetBackgroundColor = Color.DarkGray,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
    ) {
        Column {
            Text(
                text = "Characters",
                color = Color.LightGray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 130.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                if (characters.loadState.refresh == LoadState.Loading) {
                    item {
                        Text(
                            text = stringResource(id = R.string.loading_data),
                            modifier = Modifier
                                .fillMaxSize(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                items(
                    count = characters.itemCount,
                ) { index ->

                    val item = characters[index]
                    item?.let {
                        CharacterItem(it) {
                            viewModel.getDetailInfoAboutCharacter(it.id)
                            coroutineScope.launch {
                                if (bottomSheetState.isVisible) {
                                    bottomSheetState.hide()
                                } else {
                                    bottomSheetState.show()
                                }
                            }

                        }
                    }
                }
                if (characters.loadState.append == LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        )
                    }
                }
                characters.apply {
                    when {
                        loadState.refresh is LoadState.Error -> {
                            val e = loadState.refresh as LoadState.Error
                            if (e.error is UnknownHostException) {
                                item {
                                    ErrorView()
                                }
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            val e = loadState.refresh as LoadState.Error
                            if (e.error is UnknownHostException) {
                                item {
                                    ErrorView()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}