package com.example.littlelemon.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val KarlaFont = FontFamily(
    Font(R.font.karla_regular, FontWeight.Black, FontStyle.Normal),
    Font(R.font.karla_regular, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.karla_regular, FontWeight.ExtraBold, FontStyle.Normal)
)

val Markazi = FontFamily(
    Font(R.font.markazitext_regular, FontWeight.Black, FontStyle.Normal),
    Font(R.font.markazitext_regular, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.markazitext_regular, FontWeight.ExtraBold, FontStyle.Normal)
)

// Set of Material typography styles to start with
val Typography = Typography(
    KarlaFont,
    body1 = TextStyle(
        fontFamily = KarlaFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),


//    button = TextStyle(
//        fontFamily = KarlaFont,
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
//    caption = TextStyle(
//        fontFamily = KarlaFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )

)