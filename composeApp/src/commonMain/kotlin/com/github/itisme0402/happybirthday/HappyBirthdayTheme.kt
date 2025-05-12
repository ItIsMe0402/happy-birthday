package com.github.itisme0402.happybirthday

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun HappyBirthdayTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = MaterialTheme.colors.copy(
            primary = Colors.PaleRed,
        ),
        content = content,
    )
}
