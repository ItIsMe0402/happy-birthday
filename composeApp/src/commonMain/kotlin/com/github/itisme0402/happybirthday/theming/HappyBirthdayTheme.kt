package com.github.itisme0402.happybirthday.theming

import androidx.compose.foundation.shape.RoundedCornerShape
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
        shapes = MaterialTheme.shapes.copy(
            small = RoundedCornerShape(50)
        ),
        content = content,
    )
}
