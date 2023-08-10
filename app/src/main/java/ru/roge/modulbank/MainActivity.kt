package ru.roge.modulbank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import org.koin.android.ext.android.inject
import ru.roge.modulbank.presentation.CharacterViewModel
import ru.roge.modulbank.presentation.CharactersScreen
import ru.roge.modulbank.ui.theme.ModulBankTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: CharacterViewModel by inject()
        setContent {
            ModulBankTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CharactersScreen(viewModel)
                }
            }
        }
    }
}

