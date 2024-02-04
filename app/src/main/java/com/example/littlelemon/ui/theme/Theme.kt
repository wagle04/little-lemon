package com.example.littlelemon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val primaryColor = Color(0xFF495E57)
val primaryColor2= Color(0xFFF4CE14)
val secondaryColor = Color(0xffEE9972)
val secondaryColor2 = Color(0xffFBDABB)
val highlightColor = Color(0xffEDEFEE)
val highlightColor2 = Color(0xff333333)

private val DarkColorPalette = darkColors(
    primary = primaryColor,
    primaryVariant = Color(0x66495E57),
    secondary = secondaryColor
)

private val LightColorPalette = darkColors(
    primary = primaryColor,
    primaryVariant = Color(0x66495E57),
    secondary = secondaryColor
)

//private val LightColorPalette = lightColors(
//    primary = Purple500,
//    primaryVariant = Purple700,
//    secondary = Teal200
//
//    /* Other default colors to override
//    background = Color.White,
//    surface = Color.White,
//    onPrimary = Color.White,
//    onSecondary = Color.Black,
//    onBackground = Color.Black,
//    onSurface = Color.Black,
//    */
//)

@Composable
fun LittleLemonTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}