package ru.roge.modulbank.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun HelpIcon(icon: ImageVector, tint: Color = Color.LightGray) {
    Icon(
        imageVector = icon,
        contentDescription = "",
        tint= tint
    )
}