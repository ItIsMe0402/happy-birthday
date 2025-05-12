package com.github.itisme0402.happybirthday.theming

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

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
        typography = MaterialTheme.typography.copy(
            h5 = MaterialTheme.typography.h5.copy(
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        ),
        content = content,
    )
}
